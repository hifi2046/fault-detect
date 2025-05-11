package com.hifiax.fault_detect.controller;

import com.hifiax.fault_detect.dao.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class AlertController {

    @Autowired
    private AlertMapper alertMapper;

    @GetMapping("/alert")
    public String getAlertData() {
        List<String> list=alertMapper.selectList();
        System.out.println(list;
    }
}
