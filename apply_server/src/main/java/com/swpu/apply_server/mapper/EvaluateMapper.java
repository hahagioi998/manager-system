package com.swpu.apply_server.mapper;

import com.swpu.apply_server.domain.Customer;
import com.swpu.apply_server.domain.Evaluate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EvaluateMapper {

    @Select("select evl.apply_name,evl.customer_name,evl.phone_model,e.id,e.theme,e.content,e.evaluate_time,evl.apply_system from \n" +
            "(select a.id,a.apply_name,a.apply_system,c.customer_name,c.phone_model from apply a,customer_apply ca,customer c where a.id=ca.a_id and ca.c_id=c.id and a.id = #{id}) evl right join evaluate e on evl.id=e.a_id limit #{page},#{limit}")
    List<Evaluate> findEvaluateByAId(String id,Integer page,Integer limit);

    /**
     * 查看通过apply的id查看评价条数
     * @param id
     * @return
     */
    @Select("select count(1) from evaluate where a_id = #{id}")
    int countFindEvaluateByAId(String id);

    /**
     * 删除该条评价信息
     * @param id
     */
    @Delete("delete from evaluate where id = #{id}")
    void deleteEvaluateById(String id);

    /**
     * 通过应用名称查看评价信息
     * @param applyName
     * @return
     */
    @Select("select evl.apply_name,evl.customer_name,evl.phone_model,e.id,e.theme,e.content,e.evaluate_time,evl.apply_system from \n" +
            "(select a.id,a.apply_name,a.apply_system,c.customer_name,c.phone_model from apply a,customer_apply ca,customer c where a.id=ca.a_id and ca.c_id=c.id and a.apply_name = #{applyName}) evl right join evaluate e on evl.id=e.a_id limit #{page},#{limit}")
    List<Evaluate> findEvaluateByApplyName(String applyName,Integer page,Integer limit);

    /**
     * 通过应用名称查看评价条数
     * @param applyName
     * @return
     */
    @Select("select count(1) from evaluate where a_id = (select id from apply where apply_name = #{applyName})")
    int countFindEvaluateByApplyName(String applyName);

    /**
     * 通过应用系统信息查看评价信息
     * @param applySystem
     * @param page
     * @param limit
     * @return
     */
    @Select("select evl.apply_name,evl.customer_name,evl.phone_model,e.id,e.theme,e.content,e.evaluate_time,evl.apply_system from \n" +
            "(select a.id,a.apply_name,a.apply_system,c.customer_name,c.phone_model from apply a,customer_apply ca,customer c where a.id=ca.a_id and ca.c_id=c.id and a.apply_system = #{applySystem}) evl right join evaluate e on evl.id=e.a_id limit #{page},#{limit}")
    List<Evaluate> findEvaluateByApplySystem(String applySystem,Integer page,Integer limit);
    /**
     * 通过应用系统信息查看评价条数
     * @param applySystem
     * @return
     */
    @Select("select count(1) from evaluate where a_id = (select id from apply where apply_system = #{applySystem})")
    int countFindEvaluateByApplySystem(String applySystem);

    /**
     * 通过应用系统信息和应用名称查看应用评价信息
     * @param applySystem
     * @param applyName
     * @param page
     * @param limit
     * @return
     */
    @Select("select evl.apply_name,evl.customer_name,evl.phone_model,e.id,e.theme,e.content,e.evaluate_time,evl.apply_system from \n" +
            "(select a.id,a.apply_name,a.apply_system,c.customer_name,c.phone_model from apply a,customer_apply ca,customer c where a.id=ca.a_id and ca.c_id=c.id and a.apply_name = #{applyName} and a.apply_system = #{applySystem}) evl right join evaluate e on evl.id = e.a_id limit #{page},#{limit}")
    List<Evaluate> findEvaluateByApplySystemAndApplyName(String applySystem,String applyName,Integer page,Integer limit);
    /**
     * 通过应用系统信息和应用名称查看应用评价信息条数
     * @param applySystem
     * @param applyName
     * @return
     */
    @Select("select count(1) from evaluate where a_id = (select id from apply where apply_system = #{applySystem} and apply_name = #{applyName})")
    int countFindEvaluateByApplySystemAndApplyName(String applyName,String applySystem);
}
