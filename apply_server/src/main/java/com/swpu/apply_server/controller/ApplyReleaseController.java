package com.swpu.apply_server.controller;

import com.swpu.apply_server.service.ApplyReleaseService;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apply/release")
public class ApplyReleaseController {
    @Autowired
    private ApplyReleaseService applyReleaseService;
    /**
     * 应用发布
     * @param id
     * @return
     */
    @RequestMapping("/release")
    public Result applyRelease(String id) {
        applyReleaseService.release(id);
        return new Result(true, StatusCode.OK, "发布成功");
    }

    /**
     * 应用下架
     * @param id
     * @return
     */
    @RequestMapping("/soldOut")
    public Result applySoldOut(String id){
        applyReleaseService.soldOut(id);
        return new Result(true,StatusCode.OK,"下架成功");
    }
}
