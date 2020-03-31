package com.swpu.apply_server.mapper;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.vo.ApplyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ApplyAuditMapper {
    /**
     * 查询所有应用信息
     * @return
     */
    @Select("SELECT id,apply_name,icon,apply_system,description,audit_state,release_state,create_time,update_time FROM apply limit #{page},#{limit}")
    List<Apply> findAllApply(ApplyVo applyVo);

    /**
     * 查询所有应用数量
     * @return
     */
    @Select("select count(1) from apply")
    int countAllApply();

    /**
     * 通过应用名称查询
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,description,audit_state,release_state,create_time,update_time from apply where apply_name = #{applyName} limit #{page},#{limit}")
    List<Apply> findByApplyName(String applyName, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 通过应用名称查询应用条数
     * @param applyName
     * @return
     */
    @Select("select count(1) from apply where apply_name = #{applyName}")
    int countFindApplyByName(String applyName);
    /**
     * 通过应用系统查询
     * @param applySystem
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,description,audit_state,release_state,create_time,update_time from apply where apply_system = #{applySystem} limit #{page},#{limit}")
    List<Apply> findByApplySystem(String applySystem,@Param("page") Integer page,@Param("limit") Integer limit);
    /**
     * 通过应用系统查询应用条数
     * @param applySystem
     * @return
     */
    @Select("select count(1) from apply where apply_system = #{applySystem}")
    int countFindApplyBySystem(String applySystem);
    /**
     * 应用名称和应用系统查询
     * @param applyName
     * @param applySystem
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,description,audit_state,release_state,create_time,update_time from apply where apply_name = #{applyName} and apply_system = #{applySystem} limit #{page},#{limit}")
    List<Apply> findByAppNameAndBySystem(String applyName,String applySystem,@Param("page") Integer page,@Param("limit") Integer limit);

    /**
     * 通过应用系统查询和应用名称应用条数
     * @param applySystem
     * @return
     */
    @Select("select count(1) from apply where apply_name = #{applyName} and apply_system = #{applySystem}")
    int countFindApplyByNameAndSystem(String applyName,String applySystem);
}
