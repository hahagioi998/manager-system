package com.swpu.system_manager.service.impl;

import com.swpu.system_manager.domain.Permission;
import com.swpu.system_manager.mapper.PermissionMapper;
import com.swpu.system_manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 通过角色id查询权限信息
     *
     * @param id
     * @return
     */
    @Override
    public List<String> queryRolePermissionIdsByRid(String id) {
        return permissionMapper.queryRolePermissionIdsByRid(id);
    }

    /**
     * 保存角色和菜单权限之间的关系
     *
     * @param rId
     * @param ids
     */
    @Override
    public void saveRolePermission(String rId, String[] ids) {
        permissionMapper.deleteRolePermissionByPid(rId);
        if(null!=ids&&ids.length>0) {
            for (String pId : ids) {
                permissionMapper.insertRolePermission(rId,pId);
            }
        }
    }

    /**
     * 查看当前权限
     *
     * @return
     */
    @Override
    public List<Permission> findAllPermission() {
        return permissionMapper.findAllPermission();
    }

    /**
     * 查询权限条数
     */
    @Override
    public int countPermission() {
        return permissionMapper.countPermission();
    }
}
