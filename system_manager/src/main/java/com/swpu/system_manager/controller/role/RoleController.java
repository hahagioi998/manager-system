package com.swpu.system_manager.controller.role;

import com.swpu.system_manager.domain.Permission;
import com.swpu.system_manager.domain.Role;
import com.swpu.system_manager.service.PermissionService;
import com.swpu.system_manager.service.RoleService;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有角色
     */
    @RequestMapping("/findAllRole")
    public Result findAllRole(){
        List<Role> roles = roleService.findAllRole();
        if (roles == null|| roles.size()==0){
            return new Result(false,StatusCode.ERROR,"所查询的数据不存在",roleService.totalCount());
        }
        return new Result(true,StatusCode.OK,"查询成功",roles,roleService.totalCount());
    }
    /**
     * 通过角色名称和状态查询
     * @param roleName
     * @param state
     * @return
     */
    @RequestMapping("/findByNameAndState")
    public Result findRoleByNameAndState(@RequestParam("roleName") String roleName,@RequestParam("state") String state) {
        List<Role> roles = roleService.findRoleByNameAndState(roleName, state);
        if (roles == null|| roles.size()==0){
            return new Result(false,StatusCode.ERROR,"所查询的数据不存在",roleService.totalCount());
        }
        return new Result(true, StatusCode.OK,"查询成功",roles,roleService.countByState(state));
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/addRole")
    public Result addRole(Role role){
        roleService.addRole(role);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    public Result updateRole(Role role){
        roleService.updateRole(role);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public Result deleteRole(String id){
        roleService.deleteRole(id);
        return new Result(true,StatusCode.DelSuccess,"删除成功");
    }

    /**
     *保存角色和权限关系
     */
    @RequestMapping("/saveRolePermission")
     public Result saveRolePermission(String rId,String[] ids){
        permissionService.saveRolePermission(rId,ids);
        return new Result(true,StatusCode.OK,"保存成功");
     }
    /**
     * 根据角色ID加载菜单和权限的树的json串
     */

    @RequestMapping("/initPermission")
    public Result initPermissionByRoleId(String id){
        /**
         * 查看当前角色拥有得权限
         */
        List<String> permissionsIds = permissionService.queryRolePermissionIdsByRid(id);
        /**
         * 查看所有权限
         */
        List<Permission> permissions = permissionService.findAllPermission();

        for (Permission permission : permissions) {
            String permissionId = permission.getId();
            permission.setLAY_CHECKED(false);
            for (String pId : permissionsIds) {
                if(pId.equals(permissionId)) {
                    permission.setLAY_CHECKED(true);
                    break;
                }
            }
        }
        return new Result(true,StatusCode.OK,"查询成功",permissions,permissionService.countPermission());
    }
}
