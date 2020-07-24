package com.example.demo.service.impl;


import com.example.demo.dao.UserRepository;
import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 管理员服务实现
 *
 * @author shanfa
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User getUserByNickname( String username) {
        return userRepository.findByNickname(username);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> listUsers( Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listSameAuthorityUsers(AuthorityEnum authority) {
        return userRepository.findByAuthority(authority);
    }

    @Override
    public void changePassword(String id, String newRawPassword) throws IllegalArgumentException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(bCryptPasswordEncoder.encode(newRawPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("未找到指定ID的管理员");
        }
    }

    @Override
    public void changBirthDate(String id, Date newBirthDate) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBirthDate(newBirthDate);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("未找到指定ID的管理员");
        }
    }


    @Override
    public void changSignificanceTag(String id, Boolean newSignificanceTag) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSignificanceTag(newSignificanceTag);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("未找到指定ID的管理员");
        }
    }

    @Override
    public void changeCellphone(String id, String newCellphone) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCellPhoneNo(newCellphone);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("未找到指定ID的管理员");
        }
    }

    @Override
    public void changeSex(String id, SexEnum newSex) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSex(newSex);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("未找到指定ID的管理员");
        }
    }

    @Override
    public User getUserByCellPhoneNo(String cellPhoneNo) {
        return userRepository.findByCellPhoneNo(cellPhoneNo);
    }

    @Override
    public User createNewUser(User user) {
        user.setCreatedTime(new Date());
        user.setLastLoginTime(new Date());
        user.setSignificanceTag(true);
        user.setMemo("");
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 指定手机号数量
     *
     * @param cellphoneNo 手机号
     * @return 手机号数量
     */
    @Override
    public Long countOfCellphoneNo(String cellphoneNo) {
        return userRepository.countByCellPhoneNo(cellphoneNo);
    }

    /**
     * 搜索患者
     *
     * @param searchTxt
     * @return
     */
    @Override
    public List<User> searchPatients(AuthorityEnum authority, String searchTxt) {
        return userRepository.findAllByAuthorityAndNicknameContains(authority, searchTxt);
    }


    @Override
    public boolean unbindCellPhoneNo(String id, String cellPhoneNo) {
        User user = userRepository.findById(id).get();
        if (!user.getCellPhoneNo().equals(cellPhoneNo)) {
            return false;
        }
        user.setCellPhoneNo(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean resetPasswordByIdAndOldPassword(String id, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
                return false;
            } else {
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        } else {
            throw new IllegalArgumentException("未找到指定ID的用户");
        }
    }





    @Override
    public boolean checkPasswordBeenSet(String id) {
        User user = userRepository.findById(id).get();
        if (StringUtils.isEmpty(user.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public int setInitPassword(String id, String password) {
        User user = userRepository.findById(id).get();
        try {
            if (!StringUtils.isEmpty(user.getPassword())) {
                return 0;
            }
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
            return 1;
        } catch (Exception ex) {
            return 2;
        }
    }

    @Override
    public boolean loginByIdAndPassword(String id, String password) {
        return bCryptPasswordEncoder.matches(password, userRepository.findById(id).get().getPassword());
    }

    /**
     * 根据手机号和密码登录
     *
     * @param cellphoneNo
     * @param password
     * @return
     */
    @Override
    public boolean loginByCellphoneNoAndPassword(String cellphoneNo, String password) {

        User user = userRepository.findByCellPhoneNo(cellphoneNo);
        if (user!=null) {
            return bCryptPasswordEncoder.matches(password, user.getPassword());
        } else {
            throw new IllegalArgumentException("未找到手机号的用户");
        }
    }



    @Override
    public UserDetails loadUserByUsername( String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username);
        if (user==null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getAuthority().toString())));
    }

}
