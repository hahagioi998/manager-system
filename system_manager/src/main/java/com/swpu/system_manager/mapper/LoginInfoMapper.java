package com.swpu.system_manager.mapper;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.vo.LoginInfoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LoginInfoMapper {

    /**
     * 查询所有登录日志
     * @param loginInfoVo
     * @return
     */
    @Select("select id,login_name,login_time,create_time from login_info limit #{page},#{limit}")
    List<LoginInfoVo> findAllLoginInfo(LoginInfoVo loginInfoVo);

    /**
     * 查看日志条数
     * @return
     */
    @Select("select count(1) from login_info")
    int countFindAllLoginInfo();

    /**
     * 条件查询
     * @param loginName
     * @param page
     * @param limit
     * @return
     */
    @Select("select id,login_name,login_time,create_time from login_info where login_name like concat ('%',#{loginName},'%') limit #{page},#{limit}")
    List<LoginInfo> conditionQuery(String loginName,Integer page,Integer limit);

    /**
     * 条件查询条数
     * @param loginName
     * @return
     */
    @Select("select count(1) from login_info where login_name like concat ('%',#{loginName},'%')")
    int countConditionQuery(String loginName);

    /**
     * 删除日志
     * @param id
     */
    @Delete("delete from login_info where id = #{id}")
    void delLoginInfo(String id);
}
