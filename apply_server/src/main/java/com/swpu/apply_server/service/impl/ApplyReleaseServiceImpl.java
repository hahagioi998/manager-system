package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.mapper.ApplyReleaseMapper;
import com.swpu.apply_server.service.ApplyReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyReleaseServiceImpl implements ApplyReleaseService {

    @Autowired
    private ApplyReleaseMapper applyReleaseMapper;
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
}
