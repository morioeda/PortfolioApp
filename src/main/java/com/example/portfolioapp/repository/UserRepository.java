package com.example.portfolioapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.ibatis.annotations.Select;
import com.example.portfolioapp.entity.UserInfo;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserInfo, Long>{

	  @Select("select * from users where name = #{name}")
      Optional<UserInfo> findByName(String name);

}