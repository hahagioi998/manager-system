package com.swpu.system_manager.controller.permission;

import com.swpu.system_manager.service.PermissionService;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/loadAllPermission")
    public Result findAllPermission(){
        return new Result(true, StatusCode.OK,"查询成功",permissionService.findAllPermission(),permissionService.countPermission());
    }
}
