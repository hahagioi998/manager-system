package com.swpu.apply_server.service;

import com.swpu.apply_server.domain.Apply;

import java.util.List;

public interface ApplyUploadService {

    /**
     * 添加应用
     * @param apply
     */
    void addApply(Apply apply,List<String> printScreen);

}
