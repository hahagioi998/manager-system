package com.swpu.apply_server.service;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.vo.ApplyVo;

import java.util.List;

/**
 * 应用审核接口
 */
public interface ApplyAuditService {
    /**
     * 查询所有应用信息
     * @return
     */
    List<Apply> findAllApply(ApplyVo applyVo);

    /**
     * 查询所有应用数量
     * @return
     */
    int countAllApply();

    /**
     * 通过应用名称查询
     * @return
     */
    List<Apply> findByApplyName(String applyName,Integer page,Integer limit);
    /**
     * 通过应用名称查询应用条数
     * @param applyName
     * @return
     */
    int countFindApplyByName(String applyName);
    /**
     * 通过应用系统查询
     * @param applySystem
     * @return
     */
    List<Apply> findByApplySystem(String applySystem,Integer page,Integer limit);
    /**
     * 通过应用系统查询应用条数
     * @param applySystem
     * @return
     */
    int countFindApplyBySystem(String applySystem);
    /**
     * 应用名称和应用系统查询
     * @param applyName
     * @param applySystem
     * @return
     */
    List<Apply> findByAppNameAndBySystem(String applyName,String applySystem,Integer page,Integer limit);

    /**
     * 通过应用系统查询和应用名称应用条数
     * @param applySystem
     * @return
     */
    int countFindApplyByNameAndSystem(String applyName,String applySystem);

    /**
     * 应用审核通过
     * @param id
     * @return
     */
    void auditPass(String id);

    /**
     * 应用审核未通过
     * @param id
     */
    void auditUnPass(String id);
}
