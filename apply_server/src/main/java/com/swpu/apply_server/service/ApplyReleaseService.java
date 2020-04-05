package com.swpu.apply_server.service;

public interface ApplyReleaseService {
    /**
     * 应用发布
     * @param id
     */
    void release(String id);

    /**
     * 应用下架
     * @param id
     */
    void soldOut(String id);
}
