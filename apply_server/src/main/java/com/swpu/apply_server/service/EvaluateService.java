package com.swpu.apply_server.service;

import com.swpu.apply_server.domain.Evaluate;

import java.util.List;

public interface EvaluateService {
    /**
     * 查询所有评价信息
     * @param id
     * @param page
     * @param limit
     * @return
     */
    List<Evaluate> findEvaluate(String id,Integer page,Integer limit);

    /**
     * 查看通过apply的id查看评价条数
     * @param id
     * @return
     */
    int countFindEvaluateByAId(String id);

    /**
     * 通过id删除该评价信息
     * @param id
     */
    void deleteEvaluateById(String id);

    /**
     * 通过应用名称查看评价信息
     * @param applyName
     * @return
     */
    List<Evaluate> findEvaluateByApplyName(String applyName,Integer page,Integer limit);

    /**
     * 通过应用名称查看评价条数
     * @param applyName
     * @return
     */
    int countFindEvaluateByApplyName(String applyName);

    /**
     * 通过应用系统信息查看评价信息
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    List<Evaluate> findEvaluateByApplySystem(String applySystem,Integer page,Integer limit);
    /**
     * 通过应用系统信息查看评价条数
     * @param applySystem
     * @return
     */
    int countFindEvaluateByApplySystem(String applySystem);
}
