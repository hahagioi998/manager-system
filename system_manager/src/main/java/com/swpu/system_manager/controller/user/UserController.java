package com.swpu.system_manager.controller.user;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.domain.Role;
import com.swpu.system_manager.domain.User;
import com.swpu.system_manager.service.RoleService;
import com.swpu.system_manager.service.UserService;
import com.swpu.system_manager.vo.UserVo;
import domain.Result;
import domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        User loginUser = userService.login(user);
        if (loginUser == null) {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        String loginId = loginUser.getId();
        List<String> roles = roleService.queryPermissionByUId(loginId);
        String state = userService.findById(loginId).getState();
        if (!"启用".equals(state)){
            throw new RuntimeException("该用户被禁用,请联系超级管理员");
        }
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginName(userService.findById(loginId).getUserName());
        userService.saveLoginInfo(loginInfo);
        String token = jwtUtil.createJWT(loginUser.getId(), user.getPhoneNumber(), roles,state);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", roles);
        map.put("id",loginUser.getId());
        int count = map.size();
        return new Result(true, StatusCode.OK, "登录成功", map, count);
    }

    /**
     * 添加用户信息
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Result addUser(UserVo userVo) {
        if ("启用".equals(userVo.getState())){
            userVo.setState("1");
        }
        if ("禁用".equals(userVo.getState())){
            userVo.setState("-1");
        }
        userService.addUser(userVo);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll(UserVo userVo) {
        int page = (userVo.getPage()-1)*userVo.getLimit();
        userVo.setPage(page);
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll(userVo), userService.loadAll());
    }

    /**
     * 通过电话号码和状态查询
     * @param phoneNumber
     * @param state
     * @return
     */
    @RequestMapping(value = "/byPhoneAndState", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result findByPhoneNumberAndState(@RequestParam String phoneNumber,@RequestParam String state,@RequestParam Integer page,@RequestParam Integer limit) {

        page = (page - 1) * limit;
        if ("启用".equals(state)){
            state="1";
        }
        if ("禁用".equals(state)){
            state="-1";
        }
        if (userService.findByPhoneNumberAndState(phoneNumber,state,page,limit)==null){
            return new Result(false, StatusCode.ERROR, "没有该信息",0);
        }
        return new Result(true, StatusCode.OK, "查询成功", userService.findByPhoneNumberAndState(phoneNumber,state,page,limit), userService.countFindByPhoneNumberAndState(phoneNumber,state));
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result findById(@PathVariable String id) {
        if (userService.findById(id) == null) {
            return new Result(true, StatusCode.ERROR, "查询失败", userService.findById(id), 0);
        }
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id), 1);
    }
    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Result updateUser(User user) {
        if ("启用".equals(user.getState())){
            user.setState("1");
        }
        if ("禁用".equals(user.getState())){
            user.setState("-1");
        }
        System.out.println(user.toString());
        userService.updateUser(user,user.getId());
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delUser", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Result delUser(@RequestParam String id) {

        userService.delUser(id);
        return new Result(true, StatusCode.DelSuccess, "删除成功");
    }

    /**
     * 密码重置
     * @param id
     * @return
     */
    @RequestMapping(value = "/reSetPwd")
    public Result reSetPassword(String id){
        userService.reSetPassword(id);
        return new Result(true,StatusCode.OK,"重置密码成功");
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("/saveUserRole")
    public Result saveUserRole(String uId,String[] ids){
        userService.saveUserRole(uId,ids);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    /**
     * 初始化角色
     * @param id
     * @return
     */
    @RequestMapping("/initRoleByUserId")
    public Result initRoleByUserId(String id){
        //1,查询所有可用的角色
        List<Role> ableUseRoles= roleService.ableToUseRole();
        System.out.println(ableUseRoles.toString());
        //2,查询当前用户拥有的角色ID集合
        List<String> currentUserRoleIds = roleService.queryUserRoleIdsByUid(id);
        System.out.println(currentUserRoleIds.size());
        for (Role role : ableUseRoles) {
            String roleId=role.getId();
            role.setLAY_CHECKED(false);
            for (String rId : currentUserRoleIds) {
                if(rId.equals(roleId)) {
                    role.setLAY_CHECKED(true);
                    break;
                }
            }
        }

        return new Result(true,StatusCode.OK,"查找成功",ableUseRoles,roleService.countAbleToUseRole());
    }
}
