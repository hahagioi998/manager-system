package com.swpu.system_manager.service.impl;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.domain.User;
import com.swpu.system_manager.mapper.RoleMapper;
import com.swpu.system_manager.mapper.UserMapper;
import com.swpu.system_manager.service.UserService;
import com.swpu.system_manager.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private HttpServletRequest request;

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public int loadAll() {
        return userMapper.loadAll();
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAll(UserVo userVo) {
        return userMapper.findAll(userVo);
    }

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        User user = userMapper.findById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    /**
     * 添加用户信息
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setId(idWorker.nextId()+"" );
        user.setPassword(encoder.encode("123"));
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setCreateTime(time);
        user.setUpdateTime(time);
        String token = (String) request.getAttribute("claims_superAdmin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        userMapper.addUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @param id
     */
    @Override
    public void updateUser(User user, String id) {
        String token = (String) request.getAttribute("claims_superAdmin");
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        userMapper.updateUser(user,id);
    }

    /**
     * 删除用户信息信息
     *
     * @param id
     */
    @Override
    public void delUser(String id) {
        String token = (String) request.getAttribute("claims_superAdmin");
        System.out.println("claims_superAdmin:"+token);
        if (token == null || "".equals(token)){
            throw new RuntimeException("权限不足");
        }
        userMapper.delUser(id);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User userLogin = userMapper.findByPhoneNumber(user.getPhoneNumber());
        if (userLogin != null && encoder.matches(user.getPassword(),userLogin.getPassword())){
            return userLogin;
        }
        return null;
    }



    /**
     * 通过手机号码和状态查询用户
     * @param phoneNumber
     * @param state
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<User> findByPhoneNumberAndState(String phoneNumber,String state,Integer page,Integer limit) {
        List<User> users = userMapper.findByPhoneNumberAndState(phoneNumber,state,page,limit);
        if (users.size()==0||users ==null){
            return null;
        }
        return users;
    }
    /**
     * 通过手机号码和状态查询用户查询条数
     *
     * @param phoneNumber
     * @param state
     * @return
     */
    @Override
    public int countFindByPhoneNumberAndState(String phoneNumber, String state) {
        return userMapper.countFindByPhoneNumberAndState(phoneNumber,state);
    }



    /**
     * 重置密码
     *
     * @param id
     */
    @Override
    public void reSetPassword(String id) {
        userMapper.reSetPassword(id,encoder.encode("123"));
    }

    /**
     * 保存用户和角色关系
     * @param uId
     * @param ids
     */
    @Override
    public void saveUserRole(String uId, String[] ids) {
        //根据用户ID删除role_user里面的数据
        roleMapper.deleteRoleUserByUid(uId);
        if(null!=ids&&ids.length>0) {
            for (String rId : ids) {
                System.out.println("rId:"+rId);
                roleMapper.insertUserRole(uId,rId);
            }
        }
    }

    /**
     * 保存登录日志
     *
     * @param loginInfo
     */
    @Override
    public void saveLoginInfo(LoginInfo loginInfo) {
        loginInfo.setId(idWorker.nextId()+"");
        userMapper.saveLoginInfo(loginInfo);
    }
}
