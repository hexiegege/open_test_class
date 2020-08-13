package com.example.demo.dao;


import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author Dion
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, String> {


}

