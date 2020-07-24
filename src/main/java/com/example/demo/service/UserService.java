package com.example.demo.service;


import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务
 *
 * @author shanfa
 */
public interface UserService {
    /**
     * 根据昵称获取用户
     *
     * @param username 昵称
     * @return User
     */
    User getUserByNickname( String username );

    /**
     * 根据ID获取用户
     *
     * @param id 用户id
     * @return User
     */
    User getUserById( String id );


    /**
     * 根据分页获取用户
     *
     * @param pageable 分页
     * @return Page<User>
     */
    Page<User> listUsers( Pageable pageable );

    /**
     * 获取所有用户
     *
     * @return List<User>
     */
    List<User> listAllUsers();

    /**
     * 获取所有相同权限用户
     *
     * @param authority 权限
     * @return List<User>
     */
    List<User> listSameAuthorityUsers( AuthorityEnum authority );

    /**
     * 重置某ID的用户密码
     *
     * @param id             用户id
     * @param newRawPassword 新密码
     */
    void changePassword( String id, String newRawPassword );

    /**
     * 更改生日
     *
     * @param id           用户id
     * @param newBirthDate 新生日
     */
    void changBirthDate( String id, Date newBirthDate );


    /**
     * 更改可用标识
     *
     * @param id                 用户id
     * @param newSignificanceTag 可用标识
     */
    void changSignificanceTag( String id, Boolean newSignificanceTag );

    /**
     * 更改手机号
     *
     * @param id           用户id
     * @param newCellphone 新的手机号码
     */
    void changeCellphone( String id, String newCellphone );

    /**
     * 更改性别
     *
     * @param id     用户id
     * @param newSex 新的性别
     */
    void changeSex( String id, SexEnum newSex );



    /**
     * 根据手机号码获取用户
     *
     * @param cellPhoneNo 手机号码
     * @return User
     */
    User getUserByCellPhoneNo( String cellPhoneNo );

    /**
     * 创建新的用户
     *
     * @param user 新用户实例
     * @return 保存后的User类实例
     */
    User createNewUser( User user );


    /**
     * 更新用户
     *
     * @param user
     */
    User updateUser( User user );

    /**
     * 指定手机号数量
     *
     * @param cellphoneNo
     * @return
     */
    Long countOfCellphoneNo( String cellphoneNo );


    /**
     * 搜索人员
     *
     * @param authority
     * @param searchTxt
     * @return
     */
    List<User> searchPatients( AuthorityEnum authority, String searchTxt );



    /**
     * 解绑手机号码
     *
     * @param id
     * @param mobileNo
     * @return
     */
    boolean unbindCellPhoneNo( String id, String mobileNo );

    /**
     * 根据用户id和旧密码重置密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean resetPasswordByIdAndOldPassword( String id, String oldPassword, String newPassword );



    /**
     * 检查密码是否已经设置
     *
     * @param id
     * @return
     */
    boolean checkPasswordBeenSet( String id );

    /**
     * 设置初始密码
     *
     * @param id
     * @param password
     * @return
     */
    int setInitPassword( String id, String password );


    /**
     * 根据id和密码登录
     *
     * @param id
     * @param password
     * @return
     */
    boolean loginByIdAndPassword( String id, String password );

    /**
     * 根据手机号和密码登录
     * @param cellphoneNo
     * @param password
     * @return
     */
    boolean loginByCellphoneNoAndPassword( String cellphoneNo, String password );

}
