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
}
