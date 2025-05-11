#!/usr/bin/env python
# coding=utf8

import os
import sys

from mns.account import Account
from mns.queue import *

if len(sys.argv)!=2:
    print("Usage: python send.py {n}")
    sys.exit()

i=int(sys.argv[1])

message_buffer=[
    '1|A123456789|W|100.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:10|2025-05-09 12:00',
    '2|A123456789|W|150.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:20|2025-05-09 12:10',
    '3|A123456789|W|1000.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:30|2025-05-09 12:20',
    '4|A123456789|W|100.00|100.00|40.00|2000.0|2000.0|1020.0|1020.0|2025-05-09 12:40|2025-05-09 12:30'
]

endpoint='http://1806018482916119.mns.cn-hongkong.aliyuncs.com'
token=''

my_account = Account(endpoint, accessKeyId, accessKeySecret, token)
queue_name = 'trade'
my_queue = my_account.get_queue(queue_name)

try:
    msg_body = message_buffer[i]
    msg = Message(msg_body)
    re_msg = my_queue.send_message(msg)
    print("Send Message Succeed! MessageBody: %s MessageID: %s ReceiptHandle: %s" % (
        msg_body, re_msg.message_id, re_msg.receipt_handle))
except MNSExceptionBase as e:
    if e.type == "QueueNotExist":
        print("Queue not exist, please create queue before send message.")
        sys.exit(0)
    print("Send Message Fail! Exception: %s\n" % e)
