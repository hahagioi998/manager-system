package com.swpu.system_manager.mapper;

import com.swpu.system_manager.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    @Select("select id FROM role WHERE id in(SELECT r_id from user_role  where u_id = #{id})")
    List<String> queryUserRoleIdsByUid(@Param("id") String id);

    /**
     * 通过用户id查询权限集合
     * @param id
     * @return
     */
    @Select("SELECT permission_name FROM permission where id in (\n" +
            "select p_id FROM role_permission where r_id in(\n" +
            "SELECT r_id from user_role where u_id = #{id}\n" +
            "\t)\n" +
            ")")
    List<String> queryPermissionByUId(String id);
    /**
     * 根据用户ID删除用户角色中间表的数据
     * @param uId
     */
    @Delete("delete from user_role where u_id = #{uId}")
    void deleteRoleUserByUid(@Param("uId") String uId);

    /**
     * 保存角色和用户的关系
     * @param uId
     * @param rId
     */
    @Insert("insert into user_role(u_id,r_id,create_time,update_time) values (#{uId},#{rId},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'),DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'))")
    void insertUserRole(@Param("uId")String uId, @Param("rId")String rId);

    /**
     * 查询当前可用角色
     * @return
     */
    @Select("select id,role_name,description,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM role where state = '1' ")
    List<Role> ableToUseRole();

    /**
     * 查询当前可用角色的条数
     * @return
     */
    @Select("select count(1) FROM role where state = '1' ")
    int countAbleToUseRole();

    /**
     * 查询当前不可用角色条数
     * @return
     */
    @Select("select count(1) from role where state = '-1'")
    int countUnAbleToUseRole();

    /**
     * 通过状态查询条数
     * @param state
     * @return
     */
    @Select("select count(1) from role where state = #{state}")
    int countByState(@Param("state") String state);
    /**
     * 查询所有条数
     * @return
     */
    @Select("select count(1) from role")
    int totalCount();

    /**
     * 查询所有角色信息
     * @return
     */

    @Select("select id,role_name,description,(case state when 1 then '启用' when -1 then '禁用' end)state,update_time,create_time from role")
    List<Role> finAllRole();
    /**
     * 通过角色名称和状态查询角色信息
     * @param roleName
     * @param state
     * @return
     */
    @Select("select id,role_name,description,(case state when 1 then '启用' when -1 then '禁用' end)state,update_time,create_time from role where role_name = #{roleName} or state = #{state}")
    List<Role>  findRoleByNameAndState(@Param("roleName") String roleName,@Param("state") String state);

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role (id,role_name,description,state,update_time,create_time) values (#{id},#{roleName},#{description},#{state},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'),DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'))")
    void addRole(Role role);

    /**
     * 更新角色信息
     * @param role
     */
    @Update("update role set role_name = #{roleName},description = #{description},state = #{state},update_time = DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s') where id = #{id} ")
    void updateRole(Role role);

    /**
     * 删除角色
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteRole(String id);


}
