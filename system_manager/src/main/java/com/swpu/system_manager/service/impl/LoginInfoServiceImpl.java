package com.swpu.system_manager.service.impl;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.mapper.LoginInfoMapper;
import com.swpu.system_manager.service.LoginInfoService;
import com.swpu.system_manager.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginInfoServiceImpl implements LoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    /**
     * 查看所有登录日志
     *
     * @param loginInfoVo
     * @return
     */
    @Override
    public List<LoginInfoVo> findAllLoginInfo(LoginInfoVo loginInfoVo) {
        return loginInfoMapper.findAllLoginInfo(loginInfoVo);
    }

    /**
     * 查看日志条数
     *
     * @return
     */
    @Override
    public int countFindAllLoginInfo() {
        return loginInfoMapper.countFindAllLoginInfo();
    }

    /**
     * 条件查询
     *
     * @param loginName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<LoginInfo> conditionQuery(String loginName, Integer page, Integer limit) {
        return loginInfoMapper.conditionQuery(loginName,page,limit);
    }

    /**
     * 条件查询条数
     *
     * @param loginName
     * @return
     */
    @Override
    public int countConditionQuery(String loginName) {
        return loginInfoMapper.countConditionQuery(loginName);
    }

    /**
     * 删除日志
     *
     * @param id
     */
    @Override
    public void delLoginInfo(String id) {
        loginInfoMapper.delLoginInfo(id);
    }
}
