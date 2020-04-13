package com.swpu.system_manager.mapper;

import com.swpu.system_manager.domain.LoginInfo;
import com.swpu.system_manager.domain.User;
import com.swpu.system_manager.vo.UserVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User数据访问层
 */
@Component
public interface UserMapper {

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select count(1) FROM user")
    int loadAll();
    /**
     * 查询所有用户信息
     * @param userVo
     * @return
     */
    @Select("select id,user_name,sex,phone_number,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM user limit #{page},#{limit}")
    List<User> findAll(UserVo userVo);


    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @Select("select id,user_name,sex,phone_number,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM user where id = #{id}")
    User findById(String id);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Select("select id,user_name,sex,phone_number,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM user where user_name = #{userName}")
    User findByUserName(String userName);

    /**
     * 通过电话号码或状态查询用户信息
     *
     * @return
     */
    @Select("select id,user_name,sex,phone_number,create_time,update_time,\n" +
            "(case state when 1 then '启用' when -1 then '禁用' end) \n" +
            "state FROM user\n" +
            "WHERE phone_number = #{phoneNumber}\n" +
            "or state = #{state} limit #{page},#{limit}")
    List<User> findByPhoneNumberAndState(@Param("phoneNumber") String phoneNumber,@Param("state") String state,@Param("page") Integer page,@Param("limit") Integer limit);

    /**
     * 通过电话号码或状态查询用户信息
     * @param phoneNumber
     * @param state
     * @return
     */
    @Select("select count(1) FROM user WHERE phone_number = #{phoneNumber} or state = #{state}")
    int countFindByPhoneNumberAndState(@Param("phoneNumber") String phoneNumber,@Param("state") String state);
    /**
     * 添加用户信息
     *
     * @param user
     */
//    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("Insert into user(id,user_name,password,sex,phone_number,state,update_time,create_time) values (#{id},#{userName},#{password},#{sex},#{phoneNumber},#{state},#{updateTime},#{createTime})")
    void addUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     * @param id
     */
    @Update("update user set user_name = #{user.userName},sex = #{user.sex},phone_number = #{user.phoneNumber},state = #{user.state},update_time = DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s') where id = #{id}")
    void updateUser(User user, String id);

    /**
     * 删除用户信息信息
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void delUser(@Param("id") String id);

    /**
     * 更具密码和用户查询用户信息
     *
     * @param user
     * @return
     */
    @Select("select id,user_name,sex,phone_number,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM user where phone_number = #{phoneNumber} and password = #{password}")
    User findByUserNameAndPassword(User user);

    /**
     * 通过电话号码查询用户
     *
     * @param phoneNumber
     * @return
     */
    @Select("select id,user_name,sex,phone_number,password,create_time,update_time,(case state when 1 then '启用' when -1 then '禁用' end) state FROM user where phone_number = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    /**
     * 重置密码
     * @param id
     * @param password
     */
    @Update("update user set password = #{password} where id = #{id}")
    void reSetPassword(String id,String password);

    /**
     * 保存登录日志
     * @param loginInfo
     */
    @Insert("insert into login_info(id,login_name,login_time,create_time) values (#{id},#{loginName},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'),DATE_FORMAT(NOW(),'%Y-%m-%d %H:%m:%s'))")
    void saveLoginInfo(LoginInfo loginInfo);
}
