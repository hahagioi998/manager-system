package com.swpu.system_manager.service;

import com.swpu.system_manager.domain.User;
import com.swpu.system_manager.vo.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    int loadAll();
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll(UserVo userVo);
    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    User findByUserName(String userName);


    /**
     * 添加用户信息
     * @param user
     */
    void addUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @param id
     */
    void updateUser(User user,String id);

    /**
     * 删除用户信息信息
     * @param id
     */
    void delUser(String id);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 通过手机号码和状态查询用户
     * @param phoneNumber
     * @param state
     * @param page
     * @param limit
     * @return
     */
    List<User> findByPhoneNumberAndState(String phoneNumber,String state,Integer page,Integer limit);

    /**
     * 查询条数
     * @param phoneNumber
     * @param state
     * @return
     */
    int countFindByPhoneNumberAndState(String phoneNumber,String state);

    /**
     * 重置密码
     * @param id
     */
    void reSetPassword(String id);

    /**
     *保存用户和角色关系
     * @param uid
     * @param ids
     */
    void saveUserRole(String uid, String[] ids);
}
