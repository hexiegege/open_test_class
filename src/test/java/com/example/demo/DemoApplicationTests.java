package com.example.demo;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;

	@Test
	public void contextLoads() {
		System.out.println(bCryptPasswordEncoder.encode("Admin"));
	}

	@Test
	public void test1(){
//		User user =  new User();
//		user.setCellPhoneNo("18519122415");
//		user.setPassword("$2a$10$kIbhGsqfLVQaFMIOOd4dsuHaiKBKYNEERMmwUxEobhfwAKUWR.f2u");
//		user.setSex(SexEnum.Male);
//		user.setAuthority(AuthorityEnum.STUDENT);
//		user.setCreatedTime(new Date());
//		user.setSignificanceTag(true);
//		user.setNickname("dan");
//		user.setName("大王");
//		user = userRepository.save(user);
//		System.out.println(user.getId());
//		List<User> users = userRepository.findAll();
//		System.out.println(users.get(0).getCellPhoneNo());
		Course course = new Course();
		course.setCourseName("语文");
		course = courseRepository.save(course);
	}

}
