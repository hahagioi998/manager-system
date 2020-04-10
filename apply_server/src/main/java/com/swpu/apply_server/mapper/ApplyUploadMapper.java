package com.swpu.apply_server.mapper;

import com.swpu.apply_server.domain.Apply;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface ApplyUploadMapper {
    /**
     * 添加应用
     * @param apply
     */
    @Insert("insert into apply(id,apply_name,icon,apply_system,state,create_time,update_time,description) values (#{id},#{applyName},#{icon},#{applySystem},#{state},#{createTime},#{updateTime},#{description})")
    void addApply(Apply apply);

    /**
     * 添加应用截图
     * @param id
     * @param aId
     * @param appPrintScreen
     */
    @Insert("insert into print_screen(id,url,a_id,create_time,update_time) values(#{id},#{appPrintScreen},#{aId},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'),DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'))")
    void addPrintScreen(String id, String aId, String appPrintScreen);
}
