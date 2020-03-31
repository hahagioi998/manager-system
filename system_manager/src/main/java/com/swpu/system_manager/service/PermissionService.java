package com.swpu.system_manager.service;

import com.swpu.system_manager.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 通过角色id查询权限信息
     * @param id
     * @return
     */
    List<String> queryRolePermissionIdsByRid(String id);


    /**
     * 保存角色和菜单权限之间的关系
     * @param roleId
     * @param ids
     */
    void saveRolePermission(String roleId, String[] ids);

    /**
     * 查看当前权限
     * @return
     */
    List<Permission> findAllPermission();
    /**
     * 查询权限条数
     */
    int countPermission();
}
