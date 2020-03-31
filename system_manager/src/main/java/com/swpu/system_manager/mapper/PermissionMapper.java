package com.swpu.system_manager.mapper;

import com.swpu.system_manager.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionMapper {
    /**
     * 通过角色id查询权限信息
     * @param id
     * @return
     */
    @Select("select id FROM permission WHERE id in (SELECT p_id from role_permission where r_id = #{id})")
    List<String> queryRolePermissionIdsByRid(@Param("id") String id);

    /**
     *向role_permission表中添加权限权限信息
     * @param rId
     * @param pId
     */
    @Insert("insert into role_permission(r_id,p_id,create_time,update_time)values(#{rId},#{pId},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'),DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'))")
    void insertRolePermission(String rId, String pId);

    /**
     * 更具角色id删除权限表信息
     * @param rId
     */
    @Delete("delete from role_permission where r_id = #{rId}")
    void  deleteRolePermissionByPid(String rId);

    /**
     * 查询当前权限
     * @return
     */
    @Select("select id,permission_name,description,create_time,update_time FROM permission")
    List<Permission> findAllPermission();

    /**
     * 查询所有权限条数
     * @return
     */
    @Select("select count(1) from permission")
    int countPermission();

}
