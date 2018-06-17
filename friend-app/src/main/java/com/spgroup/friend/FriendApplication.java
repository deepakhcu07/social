package com.spgroup.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.spgroup.friend.repository")
public class FriendApplication {
	public static void main(String []args) {
		SpringApplication.run(FriendApplication.class, args);
	}

}
