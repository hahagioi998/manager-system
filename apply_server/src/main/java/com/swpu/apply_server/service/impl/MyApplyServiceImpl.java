package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.domain.PrintScreen;
import com.swpu.apply_server.mapper.MyApplyMapper;
import com.swpu.apply_server.service.MyApplyService;
import com.swpu.apply_server.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyApplyServiceImpl implements MyApplyService {
    @Autowired
    private MyApplyMapper myApplyMapper;

    /**
     * 查询所有应用
     * @param applyVo
     * @return
     */
    @Override
    public List<ApplyVo> findAllApply(ApplyVo applyVo) {
        return myApplyMapper.findAllApply(applyVo);
    }

    /**
     * 查询应用条数
     * @return
     */
    @Override
    public Integer countAllApply() {
        return myApplyMapper.countAllApply();
    }

    /**
     * 条件查询
     *
     * @param state
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Apply> conditionQuery(String state, String applySystem, Integer page, Integer limit) {
        return myApplyMapper.conditionQuery(state,applySystem,page,limit);
    }

    /**
     * 条件查询条数
     *
     * @param state
     * @param applySystem
     * @return
     */
    @Override
    public int countConditionQuery(String state, String applySystem) {
        return myApplyMapper.countConditionQuery(state,applySystem);
    }

    /**
     * 通过id查看单个应用
     * @param id
     * @return
     */
    @Override
    public Apply findApplyById(String id) {
        return myApplyMapper.findApplyById(id);
    }

    /**
     * 查看应用截图
     *
     * @param id
     * @return
     */
    @Override
    public List<PrintScreen> findPrintScreenById(String id) {
        return myApplyMapper.finPrintScreenById(id);
    }

    /**
     * 查看应用截图数量
     *
     * @param id
     * @return
     */
    @Override
    public int countFindPrintScreenById(String id) {
        return myApplyMapper.countFindPrintScreen(id);
    }
}
