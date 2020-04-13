package com.swpu.system_manager.controller.loginInfo;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.service.LoginInfoService;
import com.swpu.system_manager.vo.LoginInfoVo;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loginInfo")
public class LoginInfoController {
    @Autowired
    private LoginInfoService loginInfoService;

    @RequestMapping("/findAllLoginInfo")
    public Result findAllLoginInfo(LoginInfoVo loginInfoVo){
        int page = (loginInfoVo.getPage()-1)*loginInfoVo.getLimit();
        loginInfoVo.setPage(page);
        List<LoginInfoVo> loginInfos = loginInfoService.findAllLoginInfo(loginInfoVo);
        if (loginInfos.size()==0){
            return new Result(false,StatusCode.ERROR,"你所查询得数据不存在");
        }
        return new Result(true, StatusCode.OK,"查询成功",loginInfos,loginInfoService.countFindAllLoginInfo());
    }
    @RequestMapping("/conditionQuery")
    public Result conditionQuery(String loginName,Integer page,Integer limit){
        page = (page - 1) * limit;
        List<LoginInfo> loginInfos = loginInfoService.conditionQuery(loginName, page, limit);
        if (loginInfos.size()==0){
            return new Result(false,StatusCode.ERROR,"你所查询得数据不存在");
        }
        return new Result(true,StatusCode.OK,"查询成功",loginInfos,loginInfoService.countConditionQuery(loginName));
    }

    /**
     * 删除日志
     * @param id
     * @return
     */
    @RequestMapping("/deleteLogInfo")
    public Result deleteLogInfo(String id){
        loginInfoService.delLoginInfo(id);
        return new Result(true,StatusCode.DelSuccess,"删除成功");
    }

    /**
     * 批量删除日志
     * @param ids
     * @return
     */
    @RequestMapping("/batchDeleteLogInfo")
    public Result batchDeleteLogInfo(@RequestParam("ids[]") List<String> ids){
        for (String id: ids) {
            loginInfoService.delLoginInfo(id);
        }
        return new Result(true,StatusCode.DelSuccess,"删除成功");
    }
}
