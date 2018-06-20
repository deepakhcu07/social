package com.spgroup.friend.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spgroup.friend.FriendApplication;
import com.spgroup.friend.api.dto.request.UserRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FriendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mvc;
	
	
	public void init() {
		
	}
	
	private String toJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }
	
	
	
//	@Test
//	public void test() throws Exception {
//		mvc.perform(get("/friends/test")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(view().name("Tested Successfully"))
//				.andDo(print());
//				
//	}
	
	@Test
	public void createUserTest() throws Exception {
		UserRequestDto user = new UserRequestDto();
		user.setEmail("abc@example.com");
		user.setName("user");
		
		String json = toJson(user);
		
		mvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
	}

}
