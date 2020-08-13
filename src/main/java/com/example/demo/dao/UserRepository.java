package com.example.demo.dao;


import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author Dion
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据id和密码查找用户
     *
     * @param id
     * @param password
     * @return
     */
    User findByIdAndPassword( String id, String password );


    /**
     * 根据昵称查找对应用户
     *
     * @param nickname 昵称
     * @return 对应昵称的用户
     */
    User findByNickname( String nickname );

    /**
     * 根据手机号码查找对应用户
     *
     * @param cellPhoneNo 手机号
     * @return 对应手机号的用户
     */
    User findByCellPhoneNo( String cellPhoneNo );

    /**
     * 根据手机号码和权限查找对应用户
     * @param authorityEnum 用户权限
     * @param cellPhoneNo 手机号
     * @return 对应手机号的用户
     */
    User findByCellPhoneNoAndAuthority( String cellPhoneNo, AuthorityEnum authorityEnum );

    /**
     * 根据权限查找所有对应用户
     *
     * @param authority 权限
     * @return 对应权限的所有用户
     */
    List<User> findByAuthority( AuthorityEnum authority );

    /**
     * 根据手机号查找数量
     * @param cellPhoneNo
     * @return
     */
    Long countByCellPhoneNo( String cellPhoneNo );


    /**
     * 根据昵称模糊搜索
     * @param authority 角色
     * @param nickName 昵称(部分)
     * @return 结果集合
     */
    List<User> findAllByAuthorityAndNicknameContains( AuthorityEnum authority, String nickName );

    /**
     * 根据权限查找所有对应管理员
     * @param authority 权限
     * @param pageable
     * @return 对应权限的所有管理员
     */
    Page<User> findAllByAuthority( AuthorityEnum authority, Pageable pageable );

    List<User> findAll( Specification specification );
}

