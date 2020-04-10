package com.swpu.apply_server.controller;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.domain.PrintScreen;
import com.swpu.apply_server.service.ApplyUploadService;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apply/uploadApply")
public class ApplyUploadController {

    @Autowired
    private ApplyUploadService applyUploadService;
    @RequestMapping(value = "/addApply",method = RequestMethod.POST)
    public Result addApply(Apply apply, @RequestParam("printScreen[]") List<String> printScreen){
        applyUploadService.addApply(apply,printScreen);
        return new Result(true, StatusCode.OK,"添加成功");
    }
}
