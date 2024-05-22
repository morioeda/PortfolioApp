package com.example.portfolioapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.ibatis.annotations.Select;
import com.example.portfolioapp.entity.UserInfo;
import java.util.Optional;

//JpaRepositoryはDBのデータを操作するための機能
public interface UserRepository extends JpaRepository<UserInfo, Long>{

	  @Select("select * from users where email = #{email}")
      Optional<UserInfo> findByEmail(String email);

	  
}