package com.swpu.apply_server.mapper;

import com.swpu.apply_server.domain.Apply;
import com.swpu.apply_server.domain.PrintScreen;
import com.swpu.apply_server.vo.ApplyVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MyApplyMapper {
    /**
     * 查询所有应用
     * @param applyVo
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,create_time,description,(case state when 1 then '审核通过' when -1 then '审核未通过' when 0 then '审核中' end) state from apply where state = '0' or state = '1' or state = '-1' limit #{page},#{limit}")
    List<ApplyVo> findAllApply(ApplyVo applyVo);
    /**
     * 查询所有应用条数
     * @return
     */
    @Select("select count(1) from apply where state = '0' or state = '1' or state = '-1'")
    Integer countAllApply();

    /**
     * 条件查询
     * @param state
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,create_time,description,(case state when 1 then '审核通过' when -1 then '审核未通过' when 0 then '审核中' end) state from apply where state = #{state} and apply_system = #{applySystem} limit #{page},#{limit}")
    List<Apply> conditionQuery(String state, String applySystem, Integer page, Integer limit);

    /**
     * 条件查询条数
     * @param state
     * @param applySystem
     * @return
     */
    @Select("select count(1) from apply where state = #{state} and apply_system = #{applySystem}")
    int countConditionQuery(String state, String applySystem);
    /**
     * 通过id查看单个应用
     * @param id
     * @return
     */
    @Select("select id,apply_name,icon,apply_system,create_time,description,(case state when 1 then '审核通过' when -1 then '审核未通过' when 0 then '审核中' end) state from apply where id = #{id}")
    Apply findApplyById(String id);

    /**
     * 查看应用截图
     * @param id
     * @return
     */
    @Select("select id,url,create_time,update_time from print_screen where a_id = #{id}")
    List<PrintScreen> finPrintScreenById(String id);

    /**
     * 查看应用截图数量
     * @param id
     * @return
     */
    @Select("select count(1) from print_screen where a_id = #{id}")
    int countFindPrintScreen(String id);
}
