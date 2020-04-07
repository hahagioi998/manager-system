package com.swpu.apply_server.controller;

import com.swpu.apply_server.domain.Evaluate;
import com.swpu.apply_server.service.EvaluateService;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;

    @RequestMapping("/findEvaluate")
    public Result findEvaluate(String id, Integer page, Integer limit) {
        page = (page - 1) * limit;
        List<Evaluate> evaluates = evaluateService.findEvaluate(id, page, limit);
        if (evaluates.size() == 0) {
            return new Result(false, StatusCode.ERROR, "你查询的数据不存在", evaluateService.countFindEvaluateByAId(id));
        }
        return new Result(true, StatusCode.OK, "查询成功", evaluates, evaluateService.countFindEvaluateByAId(id));
    }

    @RequestMapping("/deleteEvaluate")
    public Result deleteEvaluate(String id) {
        evaluateService.deleteEvaluateById(id);
        return new Result(true, StatusCode.DelSuccess, "删除成功");
    }

    @RequestMapping("/conditionQuery")
    public Result conditionQuery(String applyName, String applySystem, @RequestParam Integer page, @RequestParam Integer limit) {
        page = (page - 1) * limit;
        /**
         * 当应用名称不为空应用系统为空时
         */
        if (!"".equals(applyName) && applyName != null && (applySystem == null || "".equals(applySystem))) {
            List<Evaluate> evaluates = evaluateService.findEvaluateByApplyName(applyName, page, limit);
            if (evaluates.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", evaluateService.countFindEvaluateByApplyName(applyName));
            }
            return new Result(true, StatusCode.OK, "查询成功", evaluates, evaluateService.countFindEvaluateByApplyName(applyName));
        }
        /**
         * 当应用名称为空应用系统不为空时
         */
        if ("".equals(applyName) || applyName == null && (!"".equals(applySystem) && applySystem != null)) {
            List<Evaluate> evaluates = evaluateService.findEvaluateByApplySystem(applySystem, page, limit);
            if (evaluates.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", evaluateService.countFindEvaluateByApplySystem(applySystem));
            }
            return new Result(true, StatusCode.OK, "查询成功", evaluates, evaluateService.countFindEvaluateByApplySystem(applySystem));
        }
        /**
         * 当应用名称和应用系统都不为空时
         */
        if (!"".equals(applyName) && applyName != null && (!"".equals(applySystem) && applySystem != null)) {
            List<Evaluate> evaluates = evaluateService.findEvaluateByApplySystemAndApplyName(applyName, applySystem, page, limit);
            if (evaluates.size() == 0) {
                return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", evaluateService.countFindEvaluateByApplySystemAndApplyName(applyName, applySystem));
            }
            return new Result(true, StatusCode.OK, "查询成功", evaluates, evaluateService.countFindEvaluateByApplySystemAndApplyName(applyName, applySystem));
        }
        return new Result(false, StatusCode.ERROR, "您查询的数据不存在", 0);
    }
}
