package com.hifiax.fault_detect.controller;

import com.hifiax.fault_detect.dao.AlertMapper;
import com.hifiax.fault_detect.data.Alert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AlertController {

    @Autowired
    private AlertMapper alertMapper;

    @GetMapping("/alert")
    public List<Alert> getAlertData() {
        System.out.println("get alert data");
        List<Alert> list=alertMapper.selectList(new QueryWrapper<Alert>().orderByDesc("id"));
        System.out.println(list);
        return list;
    }
}
