package com.swpu.apply_server.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface ApplyReleaseMapper {

    /**
     * 应用发布
     * @param id
     */
    @Update("update apply set state = '2' where id = #{id}")
    void release(String id);

    /**
     * 应用下架
     * @param id
     */
    @Update("update apply set state = '-2' where id = #{id}")
    void soldOut(String id);
}
