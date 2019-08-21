package com.i2.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.i2.domain.Person;

public interface PersonInterface extends JpaRepository<Person, Long>{
	//rel相关的属性
	@RestResource(rel="nameStartwith",path="nameStartwith")
	Person findByNameStartsWith(@Param("name")String name);
	
}
