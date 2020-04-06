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
}
