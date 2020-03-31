package com.swpu.apply_server.controller;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.service.ApplyAuditService;
import com.swpu.apply_server.vo.ApplyVo;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apply/audit")
public class ApplyAuditController {
    @Autowired
    private ApplyAuditService applyAuditService;


    @RequestMapping("/findAllApply")
    public Result findAllApply(ApplyVo applyVo){
        int page = (applyVo.getPage()-1)*applyVo.getLimit();
        applyVo.setPage(page);
        List<Apply> apples = applyAuditService.findAllApply(applyVo);
        if (apples.size()==0){
            return new Result(false,StatusCode.ERROR,"您所查询的数据不存在",applyAuditService.countAllApply());
        }
        return new Result(true, StatusCode.OK,"查询成功",apples,applyAuditService.countAllApply());
    }

    @RequestMapping("/conditionQuery")
    public Result conditionsQuery(String applyName, String applySystem, @RequestParam Integer page, @RequestParam Integer limit){
        page = (page - 1) * limit;
        /**
         * 当应用名称不为空应用系统为空时
         */
        if (!"".equals(applyName)&&applyName != null &&(applySystem ==null || "".equals(applySystem))){
            List<Apply> applies = applyAuditService.findByApplyName(applyName,page,limit);
            if (applies.size()==0){
                return new Result(false,StatusCode.ERROR,"您所查询的数据不存在",applyAuditService.countFindApplyByName(applyName));
            }
            return new Result(true,StatusCode.OK,"查询成功",applies,applyAuditService.countFindApplyByName(applyName));
        }
        /**
         * 当应用名称为空应用系统不为空时
         */
        if ("".equals(applyName)||applyName == null && (!"".equals(applySystem)&&applySystem != null)){
            List<Apply> applies = applyAuditService.findByApplySystem(applySystem,page,limit);
            if (applies.size()==0){
                return new Result(false,StatusCode.ERROR,"您所查询的数据不存在",applyAuditService.countFindApplyBySystem(applySystem));
            }
            return new Result(true,StatusCode.OK,"查询成功",applies,applyAuditService.countFindApplyBySystem(applySystem));
        }
        /**
         * 当应用名称和应用系统都不为空时
         */
        if (!"".equals(applyName)&&applyName != null && (!"".equals(applySystem)&&applySystem != null)){
            List<Apply> applies = applyAuditService.findByAppNameAndBySystem(applyName, applySystem,page,limit);
            if (applies.size() == 0){
                return new Result(false,StatusCode.ERROR,"您所查询的数据不存在",applyAuditService.countFindApplyByNameAndSystem(applyName,applySystem));
            }
            return new Result(true,StatusCode.OK,"查询成功",applies,applyAuditService.countFindApplyByNameAndSystem(applyName,applySystem));
        }
        return new Result(false,StatusCode.ERROR,"您查询的数据不存在",0);
    }


}
