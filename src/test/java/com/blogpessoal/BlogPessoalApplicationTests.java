package com.blogpessoal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogpessoal.api.controller.IndexController;

@SpringBootTest
class BlogPessoalApplicationTests {
	
	@Autowired
	private IndexController index;
	@Test
	void contextLoads() {
		assertThat(index).isNotNull();
	}

}
