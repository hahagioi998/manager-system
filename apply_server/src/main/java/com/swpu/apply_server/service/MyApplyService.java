package com.swpu.apply_server.service;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.domain.PrintScreen;
import com.swpu.apply_server.vo.ApplyVo;

import java.util.List;

public interface MyApplyService {
    /**
     * 查询所有应用
     * @param applyVo
     * @return
     */
    List<ApplyVo> findAllApply(ApplyVo applyVo);

    /**
     * 查询应用条数
     * @return
     */
    Integer countAllApply();

    /**
     * 条件查询
     * @param state
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    List<Apply> conditionQuery(String state, String applySystem, Integer page, Integer limit);

    /**
     * 条件查询条数
     * @param state
     * @param applySystem
     * @return
     */
    int countConditionQuery(String state, String applySystem);
    /**
     * 通过id查看单个应用
     * @param id
     * @return
     */
    Apply findApplyById(String id);

    /**
     * 查看应用截图
     * @param id
     * @return
     */
    List<PrintScreen> findPrintScreenById(String id);

    /**
     * 查看应用截图数量
     * @param id
     * @return
     */
    int countFindPrintScreenById(String id);
}
