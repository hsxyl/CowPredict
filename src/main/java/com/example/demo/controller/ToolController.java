package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xushenbao
 * @desc 工具接口
 * @create 2019/11/1
 */
@RestController
@RequestMapping("/tool")
@CrossOrigin
public class ToolController {

    @GetMapping("/status")
    public String status() {
      return "ok";
    }
}
