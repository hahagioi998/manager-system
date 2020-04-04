package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.mapper.ApplyAuditMapper;
import com.swpu.apply_server.service.ApplyAuditService;
import com.swpu.apply_server.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplyAuditServiceImpl implements ApplyAuditService {
    @Autowired
    private ApplyAuditMapper applyAuditMapper;
    /**
     * 查询所有应用信息
     *
     * @return
     */
    @Override
    public List<Apply> findAllApply(ApplyVo applyVo) {

        return applyAuditMapper.findAllApply(applyVo);
    }

    /**
     * 查询所有应用数量
     *
     * @return
     */
    @Override
    public int countAllApply() {
        return applyAuditMapper.countAllApply();
    }

    /**
     * 通过应用名称查询
     *
     * @param applyName
     * @return
     */
    @Override
    public List<Apply> findByApplyName(String applyName,Integer page,Integer limit) {
        return applyAuditMapper.findByApplyName(applyName,page,limit);
    }

    /**
     * 通过应用名称查询应用条数
     *
     * @param applyName
     * @return
     */
    @Override
    public int countFindApplyByName(String applyName) {
        return applyAuditMapper.countFindApplyByName(applyName);
    }

    /**
     * 通过应用系统查询
     *
     * @param applySystem
     * @return
     */
    @Override
    public List<Apply> findByApplySystem(String applySystem,Integer page,Integer limit) {
        return applyAuditMapper.findByApplySystem(applySystem,page,limit);
    }

    /**
     * 通过应用系统查询应用条数
     *
     * @param applySystem
     * @return
     */
    @Override
    public int countFindApplyBySystem(String applySystem) {
        return applyAuditMapper.countFindApplyBySystem(applySystem);
    }

    /**
     * 应用名称和应用系统查询
     *
     * @param applyName
     * @param applySystem
     * @return
     */
    @Override
    public List<Apply> findByAppNameAndBySystem(String applyName, String applySystem,Integer page,Integer limit) {
        return applyAuditMapper.findByAppNameAndBySystem(applyName,applySystem,page,limit);
    }

    /**
     * 通过应用系统查询和应用名称应用条数
     *
     * @param applyName
     * @param applySystem
     * @return
     */
    @Override
    public int countFindApplyByNameAndSystem(String applyName, String applySystem) {
        return applyAuditMapper.countFindApplyByNameAndSystem(applyName,applySystem);
    }

    /**
     * 应用审核通过
     *
     * @param id
     * @return
     */
    @Override
    public void auditPass(String id) {
        applyAuditMapper.auditPass(id);
    }

    /**
     * 应用审核未通过
     *
     * @param id
     */
    @Override
    public void auditUnPass(String id) {
        applyAuditMapper.auditUnPass(id);
    }
}
