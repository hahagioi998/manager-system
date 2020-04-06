package com.swpu.apply_server.controller;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.service.ApplyReleaseService;
import com.swpu.apply_server.vo.ApplyVo;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apply/release")
public class ApplyReleaseController {
    @Autowired
    private ApplyReleaseService applyReleaseService;

    @RequestMapping("/findAllApply")
    public Result findAllApply(ApplyVo applyVo) {
        int page = (applyVo.getPage() - 1) * applyVo.getLimit();
        applyVo.setPage(page);
        List<Apply> apples = applyReleaseService.findAllApply(applyVo);
        if (apples.size() == 0) {
            return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", applyReleaseService.countAllApply());
        }
        return new Result(true, StatusCode.OK, "查询成功", apples, applyReleaseService.countAllApply());
    }

    @RequestMapping("/conditionQuery")
    public Result conditionsQuery(String applyName, String applySystem, @RequestParam Integer page, @RequestParam Integer limit) {
        page = (page - 1) * limit;
        /**
         * 当应用名称不为空应用系统为空时
         */
        if (!"".equals(applyName) && applyName != null && (applySystem == null || "".equals(applySystem))) {
            List<Apply> applies = applyReleaseService.findByApplyName(applyName, page, limit);
            if (applies.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", applyReleaseService.countFindApplyByName(applyName));
            }
            return new Result(true, StatusCode.OK, "查询成功", applies, applyReleaseService.countFindApplyByName(applyName));
        }
        /**
         * 当应用名称为空应用系统不为空时
         */
        if ("".equals(applyName) || applyName == null && (!"".equals(applySystem) && applySystem != null)) {
            List<Apply> applies = applyReleaseService.findByApplySystem(applySystem, page, limit);
            if (applies.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", applyReleaseService.countFindApplyBySystem(applySystem));
            }
            return new Result(true, StatusCode.OK, "查询成功", applies, applyReleaseService.countFindApplyBySystem(applySystem));
        }
        /**
         * 当应用名称和应用系统都不为空时
         */
        if (!"".equals(applyName) && applyName != null && (!"".equals(applySystem) && applySystem != null)) {
            List<Apply> applies = applyReleaseService.findByAppNameAndBySystem(applyName, applySystem, page, limit);
            if (applies.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", applyReleaseService.countFindApplyByNameAndSystem(applyName, applySystem));
            }
            return new Result(true, StatusCode.OK, "查询成功", applies, applyReleaseService.countFindApplyByNameAndSystem(applyName, applySystem));
        }
        return new Result(false, StatusCode.ERROR, "您查询的数据不存在", 0);
    }
    /**
     * 应用发布
     * @param id
     * @return
     */
    @RequestMapping("/release")
    public Result applyRelease(String id) {
        String state = applyReleaseService.applyState(id);
        if ("1".equals(state)){
            applyReleaseService.release(id);
            return new Result(true, StatusCode.OK, "发布成功");
        }
        return new Result(false,StatusCode.ERROR,"审核未通过或已下架，不能发布");
    }

    /**
     * 应用下架
     * @param id
     * @return
     */
    @RequestMapping("/soldOut")
    public Result applySoldOut(String id){
        String state = applyReleaseService.applyState(id);
        if ("-2".equals(state)){
            return new Result(false,StatusCode.ERROR,"该应用已经下架");
        }
        applyReleaseService.soldOut(id);
        return new Result(true,StatusCode.OK,"下架成功");
    }
    @RequestMapping("/applyState")
    public Result checkApplyState(String id){
        String state = applyReleaseService.applyState(id);
        if ("2".equals(state)|| "-2".equals(state)){
            return new Result(true,StatusCode.OK,"查看成功");
        }
        return new Result(false,StatusCode.ERROR,"该应用未发布，不能查看评价信息！");
    }
}
