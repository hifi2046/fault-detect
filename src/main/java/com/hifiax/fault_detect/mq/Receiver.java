package com.hifiax.fault_detect.mq;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.common.ServiceHandlingRequiredException;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.MessagePropertyValue;
import com.aliyun.mns.model.MessageSystemPropertyValue;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static final Boolean IS_BASE64 = true;
    public String receive() {
        String queueName = "trade";
        CloudAccount account = new CloudAccount("http://1806018482916119.mns.cn-hongkong.aliyuncs.com");
        MNSClient client = account.getMNSClient();
        CloudQueue queue = client.getQueueRef(queueName);

        String msg="";
        try {
            Message popMsg = queue.popMessage();
            if( popMsg != null) {
                msg=popMsg.getMessageBody();
                System.out.println(msg);
                queue.deleteMessage(popMsg.getReceiptHandle());
            }
        } catch (ClientException ce) {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            // 客户端异常，默认为抖动，触发下次重试
        } catch (ServiceException se) {
            if (se.getErrorCode().equals("QueueNotExist")) {
                System.out.println("Queue is not exist.Please create queue before use");
                client.close();
                return msg;
            } else if (se.getErrorCode().equals("TimeExpired")) {
                System.out.println("The request is time expired. Please check your local machine timeclock");
                return msg;
            }
            // 其他的服务端异常，默认为抖动，触发下次重试
        } catch (Exception e) {
            System.out.println("Unknown exception happened!e:"+e.getMessage());
            // 其他异常，默认为抖动，触发下次重试
        }

        client.close();
        return msg;
    }

}
