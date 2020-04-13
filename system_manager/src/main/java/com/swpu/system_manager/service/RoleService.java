package com.swpu.system_manager.service;

import com.swpu.system_manager.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<String> queryUserRoleIdsByUid(String id);

    /**
     * 通过用户ID查询权限集合
     * @param id
     * @return
     */
    List<String> queryPermissionByUId(String id);
    /**
     * 查询当前可用角色
     * @return
     */
    List<Role> ableToUseRole();
    /**
     * 查询当前可用角色条数
     * @return
     */
    int countAbleToUseRole();

    /**
     * 通过状态查询条数
     * @param state
     * @return
     */
    int countByState(String state);

    /**
     * 查询当前不可用角色条数
     * @return
     */
    int countUnAbleToUseRole();

    /**
     * 查询所有角色条数
     * @return
     */
    int totalCount();

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole();
    /**
     * 通过角色名称和状态信息查询角色
     * @param roleName
     * @param state
     * @return
     */
    List<Role>  findRoleByNameAndState(String roleName,String state);

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色信息
     * @param id
     */
    void deleteRole(String id);


}
