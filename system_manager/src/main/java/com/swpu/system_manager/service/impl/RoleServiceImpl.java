package com.swpu.system_manager.service.impl;

import com.swpu.system_manager.domain.Role;
import com.swpu.system_manager.mapper.RoleMapper;
import com.swpu.system_manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private HttpServletRequest request;

    /**
     * 查询当前用户拥有的角色ID集合
     *
     * @param id
     * @return
     */
    @Override
    public List<String> queryUserRoleIdsByUid(String id) {
        String token = (String) request.getAttribute("claims_admin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        return roleMapper.queryUserRoleIdsByUid(id);
    }

    /**
     * 通过用户ID查询权限集合
     *
     * @param id
     * @return
     */
    @Override
    public List<String> queryPermissionByUId(String id) {
        List<String> roles = roleMapper.queryPermissionByUId(id);
        if (roles.size()>0){
            return roles;
        }
        return null;
    }


    /**
     * 查询当前可用角色
     *
     * @return
     */
    @Override
    public List<Role> ableToUseRole() {
        String token = (String) request.getAttribute("claims_admin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        return roleMapper.ableToUseRole();
    }

    /**
     * 查询当前可用角色条数
     *
     * @return
     */
    @Override
    public int countAbleToUseRole() {
        return roleMapper.countAbleToUseRole();
    }

    /**
     * 通过状态查询条数
     *
     * @param state
     * @return
     */
    @Override
    public int countByState(String state) {
        return roleMapper.countByState(state);
    }

    /**
     * 查询当前不可用角色条数
     *
     * @return
     */
    @Override
    public int countUnAbleToUseRole() {
        return roleMapper.countUnAbleToUseRole();
    }

    /**
     * 查询所有角色条数
     *
     * @return
     */
    @Override
    public int totalCount() {
        return roleMapper.totalCount();
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        String token = (String) request.getAttribute("claims_admin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        return roleMapper.finAllRole();
    }

    /**
     * 通过角色名称和状态信息查询角色
     *
     * @param roleName
     * @param state
     * @return
     */
    @Override
    public List<Role> findRoleByNameAndState(String roleName, String state) {
        String token = (String) request.getAttribute("claims_admin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        return roleMapper.findRoleByNameAndState(roleName,state);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public void addRole(Role role) {
        role.setId(idWorker.nextId()+"");
        String token = (String) request.getAttribute("claims_superAdmin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        roleMapper.addRole(role);
    }

    /**
     * 更新角色
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        String token = (String) request.getAttribute("claims_superAdmin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        roleMapper.updateRole(role);
    }

    /**
     * 删除角色信息
     *
     * @param id
     */
    @Override
    public void deleteRole(String id) {
        String token = (String) request.getAttribute("claims_superAdmin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        roleMapper.deleteRole(id);
    }
}
