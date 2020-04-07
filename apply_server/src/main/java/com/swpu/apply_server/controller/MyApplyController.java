package com.swpu.apply_server.controller;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.domain.PrintScreen;
import com.swpu.apply_server.service.MyApplyService;
import com.swpu.apply_server.vo.ApplyVo;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apply/myApply")
public class MyApplyController {
    @Autowired
    private MyApplyService myApplyService;

    /**
     * 查看所有应用
     * @param applyVo
     * @return
     */
    @RequestMapping("/findAllApply")
    public Result findAllApply(ApplyVo applyVo){
        int page = (applyVo.getPage() - 1) * applyVo.getLimit();
        applyVo.setPage(page);
        List<ApplyVo> apples = myApplyService.findAllApply(applyVo);
        if (apples.size() == 0) {
            return new Result(false, StatusCode.ERROR, "您所查询的数据不存在", myApplyService.countAllApply());
        }
        return new Result(true, StatusCode.OK, "查询成功", apples, myApplyService.countAllApply());
    }

    /**
     * 条件查询
     * @param state
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/conditionQuery")
    public Result conditionQuery(String state,String applySystem,Integer page,Integer limit){
        page = (page - 1) * limit;
        List<Apply> applies = myApplyService.conditionQuery(state,applySystem,page,limit);
        int count = myApplyService.countConditionQuery(state,applySystem);
        if (applies.size()==0){
            return new Result(false,StatusCode.ERROR,"你查询的数据不存在",count);
        }
        return new Result(true,StatusCode.OK,"查询成功",applies,count);
    }

    /**
     * 查看应用详情
     * @param id
     * @return
     */
    @RequestMapping("/queryDetail")
    public Result queryDetail(String id){
        Apply apply = myApplyService.findApplyById(id);
        if (null != apply || "".equals(apply)){
            return new Result(true,StatusCode.OK,"查询成功",apply,1);
        }
        return new Result(false,StatusCode.ERROR,"你查询的数据不存在");
    }

    /**
     * 查看应用截图
     * @param id
     * @return
     */
    @RequestMapping("/printScreen")
    public Result queryPrintScreen(String id){
        List<PrintScreen> printScreens = myApplyService.findPrintScreenById(id);
        if (printScreens.size()==0){
            return new Result(false,StatusCode.ERROR,"没有查询到数据");
        }
        return new Result(true,StatusCode.OK,"查询成功",printScreens,myApplyService.countFindPrintScreenById(id));
    }
}
