package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.mapper.ApplyReleaseMapper;
import com.swpu.apply_server.service.ApplyReleaseService;
import com.swpu.apply_server.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyReleaseServiceImpl implements ApplyReleaseService {

    @Autowired
    private ApplyReleaseMapper applyReleaseMapper;

    /**
     * 查询所有应用信息
     *
     * @param applyVo
     * @return
     */
    @Override
    public List<Apply> findAllApply(ApplyVo applyVo) {
        return applyReleaseMapper.findAllApply(applyVo);
    }

    /**
     * 查询所有应用数量
     *
     * @return
     */
    @Override
    public int countAllApply() {
        return applyReleaseMapper.countAllApply();
    }

    /**
     * 通过应用名称查询
     *
     * @param applyName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Apply> findByApplyName(String applyName, Integer page, Integer limit) {
        return applyReleaseMapper.findByApplyName(applyName,page,limit);
    }

    /**
     * 通过应用名称查询应用条数
     *
     * @param applyName
     * @return
     */
    @Override
    public int countFindApplyByName(String applyName) {
        return applyReleaseMapper.countFindApplyByName(applyName);
    }

    /**
     * 通过应用系统查询
     *
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Apply> findByApplySystem(String applySystem, Integer page, Integer limit) {
        return applyReleaseMapper.findByApplySystem(applySystem,page,limit);
    }

    /**
     * 通过应用系统查询应用条数
     *
     * @param applySystem
     * @return
     */
    @Override
    public int countFindApplyBySystem(String applySystem) {
        return applyReleaseMapper.countFindApplyBySystem(applySystem);
    }

    /**
     * 应用名称和应用系统查询
     *
     * @param applyName
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Apply> findByAppNameAndBySystem(String applyName, String applySystem, Integer page, Integer limit) {
        return applyReleaseMapper.findByAppNameAndBySystem(applyName,applySystem,page,limit);
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
        return applyReleaseMapper.countFindApplyByNameAndSystem(applyName,applySystem);
    }

    /**
     * 应用发布
     *
     * @param id
     */
    @Override
    public void release(String id) {
        applyReleaseMapper.release(id);
    }

    /**
     * 应用下架
     *
     * @param id
     */
    @Override
    public void soldOut(String id) {
        applyReleaseMapper.soldOut(id);
    }

    /**
     * 查看应用状态
     *
     * @param id
     * @return
     */
    @Override
    public String applyState(String id) {
        return applyReleaseMapper.applyState(id);
    }
}
