package com.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.category.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

	List<User> findByMobile(String mobile);

	Optional<Category> findById(String id);

	Optional<Category> findByEmail(String email);

	void deleteByMobile(String mobile);

	void deleteByEmail(String email);

	List<User> findByMobileAndName(String mobile, String name);

	List<User> findByEmailAndName(String email, String name);

}
