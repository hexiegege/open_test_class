package com.example.demo.dao;

import com.example.demo.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SubscribeRepository
 * @Author shanfa
 * @Date 2020/7/31 17:37
 * @Description SubscribeRepository
 * @Version 1.0
 */
@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, String> {
}
