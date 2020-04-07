package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.domain.Evaluate;
import com.swpu.apply_server.mapper.EvaluateMapper;
import com.swpu.apply_server.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;
    /**
     * 查询所有评价信息
     * @param id
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Evaluate> findEvaluate(String id, Integer page, Integer limit) {
        return evaluateMapper.findEvaluateByAId(id,page,limit);
    }

    /**
     * 查看通过apply的id查看评价条数
     *
     * @param id
     * @return
     */
    @Override
    public int countFindEvaluateByAId(String id) {
        return evaluateMapper.countFindEvaluateByAId(id);
    }

    /**
     * 通过id删除该评价信息
     *
     * @param id
     */
    @Override
    public void deleteEvaluateById(String id) {
         evaluateMapper.deleteEvaluateById(id);
    }

    /**
     * 通过应用名称查看评价信息
     *
     * @param applyName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Evaluate> findEvaluateByApplyName(String applyName, Integer page, Integer limit) {
        return evaluateMapper.findEvaluateByApplyName(applyName,page,limit);
    }

    /**
     * 通过应用名称查看评价条数
     *
     * @param applyName
     * @return
     */
    @Override
    public int countFindEvaluateByApplyName(String applyName) {
        return evaluateMapper.countFindEvaluateByApplyName(applyName);
    }

    /**
     * 通过应用系统信息查看评价信息
     *
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Evaluate> findEvaluateByApplySystem(String applySystem, Integer page, Integer limit) {
        return evaluateMapper.findEvaluateByApplySystem(applySystem,page,limit);
    }

    /**
     * 通过应用系统信息查看评价条数
     *
     * @param applySystem
     * @return
     */
    @Override
    public int countFindEvaluateByApplySystem(String applySystem) {
        return evaluateMapper.countFindEvaluateByApplySystem(applySystem);
    }

    /**
     * 通过应用系统信息和应用名称查看应用评价信息
     *
     * @param applySystem
     * @param applyName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Evaluate> findEvaluateByApplySystemAndApplyName(String applySystem, String applyName, Integer page, Integer limit) {
        return evaluateMapper.findEvaluateByApplySystemAndApplyName(applySystem,applyName,page,limit);
    }

    /**
     * 通过应用系统信息和应用名称查看应用评价信息条数
     *
     * @param applyName
     * @param applySystem
     * @return
     */
    @Override
    public int countFindEvaluateByApplySystemAndApplyName(String applyName, String applySystem) {
        return evaluateMapper.countFindEvaluateByApplySystemAndApplyName(applyName,applySystem);
    }
}
