package com.swpu.system_manager.service;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.vo.LoginInfoVo;

import java.util.List;

public interface LoginInfoService {

    /**
     * 查看所有登录日志
     * @param loginInfoVo
     * @return
     */
    List<LoginInfoVo> findAllLoginInfo(LoginInfoVo loginInfoVo);

    /**
     * 查看日志条数
     * @return
     */
    int countFindAllLoginInfo();

    /**
     * 条件查询
     * @param loginName
     * @param page
     * @param limit
     * @return
     */
    List<LoginInfo> conditionQuery(String loginName,Integer page,Integer limit);

    /**
     * 条件查询条数
     * @param loginName
     * @return
     */
    int countConditionQuery(String loginName);

    /**
     * 删除日志
     * @param id
     */
    void delLoginInfo(String id);
}
