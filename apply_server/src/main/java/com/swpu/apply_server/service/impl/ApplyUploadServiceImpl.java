package com.swpu.apply_server.service.impl;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.mapper.ApplyUploadMapper;
import com.swpu.apply_server.service.ApplyUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ApplyUploadServiceImpl implements ApplyUploadService {
    @Autowired
    private ApplyUploadMapper applyUploadMapper;
    @Autowired
    private IdWorker idWorker;
    /**
     * 添加应用
     *
     * @param apply
     */
    @Override
    public void addApply(Apply apply,List<String> printScreen) {
        apply.setId(idWorker.nextId()+"" );
        apply.setState("0");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        apply.setCreateTime(time);
        apply.setUpdateTime(time);
        for (String appPrintScreen: printScreen) {
            String id = idWorker.nextId()+"";
            applyUploadMapper.addPrintScreen(id,apply.getId(),appPrintScreen);
        }
        applyUploadMapper.addApply(apply);
    }

}
