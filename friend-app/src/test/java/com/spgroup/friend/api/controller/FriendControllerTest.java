package com.spgroup.friend.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.spgroup.friend.api.dto.request.FriendRequestDto;
import com.spgroup.friend.api.dto.request.SearchFriendDto;
import com.spgroup.friend.api.dto.request.SubscribeRequestDto;
import com.spgroup.friend.api.dto.request.UpdateRequestDto;
import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.service.FriendService;
import com.spgroup.friend.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FriendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class FriendControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mvc;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	
	private String toJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }
	
	
	private UserRequestDto generateUserRequest(String name, String email) {
		UserRequestDto user = new UserRequestDto();
		user.setEmail(email);
		user.setName(name);
		return user;
	}
	
	private void createUsers(int id) {
		String name1 = "abc"+id;
		String email1 = name1+"@example.com";
		
		String name2 = "xyz"+id;
		String email2 = name2+"@example.com";
		UserRequestDto user1 = generateUserRequest(name1 , email1);
		UserRequestDto user2 = generateUserRequest(name2, email2);
		userService.create(user1);
		userService.create(user2);
	}
	
	@Test
	public void createFriendConnection_SuccessTest() throws Exception {
		createUsers(1);
		FriendRequestDto request = new FriendRequestDto();
		List<String> friends = new ArrayList<>();
		friends.add("abc1@example.com");
		friends.add("xyz1@example.com");
		request.setFriends(friends);
		
		String json = toJson(request);
		
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
		
	}
	
	@Test
	public void createFriendConnection_ErrorResponseWhenDataIsInvalid() throws Exception {
		// When friends is empty
		FriendRequestDto request = new FriendRequestDto();
		List<String> friends = new ArrayList<>();
		request.setFriends(friends);
		String json = toJson(request);
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
		// friends size check
		friends.add("abc@example.com");
		friends.add("xyz@example.com");
		friends.add("xyz1@example.com");
		request.setFriends(friends);
		json = toJson(request);
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
		//if both emails are equal
		friends.clear();
		friends.add("xyz@example.com");
		friends.add("xyz@example.com");
		request.setFriends(friends);
		json = toJson(request);
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
	}
	
	@Test
	public void createFriendConnection_ErrorResponseWhenUserDoesNotExist() throws Exception {
		List<String> friends = new ArrayList<>();
		friends.add("def@example.com");
		friends.add("hjk@example.com");
		FriendRequestDto request = new FriendRequestDto();
		request.setFriends(friends);
		String json = toJson(request);
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
	}
	
	@Test
	public void createFriendConnection_ErrorResponseWhenFriendConnectionAlreadyExist() throws Exception {
		createUsers(3);
		List<String> friends = new ArrayList<>();
		friends.add("abc3@example.com");
		friends.add("xyz3@example.com");
		FriendRequestDto request = new FriendRequestDto();
		request.setFriends(friends);
		String json = toJson(request);
		
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isConflict())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
		friends.clear();
		friends.add("xyz3@example.com");
		friends.add("abc3@example.com");
		request.setFriends(friends);
		json = toJson(request);
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isConflict())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
		
	}
	
	@Test
	public void getFriendList_SuccessTest() throws Exception {
		createUsers(4);
		List<String> friends = new ArrayList<>();
		friends.add("abc4@example.com");
		friends.add("xyz4@example.com");
		FriendRequestDto request = new FriendRequestDto();
		request.setFriends(friends);
		String json = toJson(request);
		
		mvc.perform(post("/friends").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
		SearchFriendDto searchRequest = new SearchFriendDto();
		searchRequest.setEmail("abc4@example.com");
		json = toJson(searchRequest);
		mvc.perform(post("/friends/search").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true))
		.andExpect(jsonPath("$.count").value(1))
		.andExpect(jsonPath("$.friends[0]").value("xyz4@example.com"));
		
		searchRequest.setEmail("xyz4@example.com");
		json = toJson(searchRequest);
		mvc.perform(post("/friends/search").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true))
		.andExpect(jsonPath("$.count").value(1))
		.andExpect(jsonPath("$.friends[0]").value("abc4@example.com"));
		
	}
	
	@Test
	public void getFriendList_ErrorResponseWhenRequestIsInvalid() throws Exception {
		SearchFriendDto searchRequest = new SearchFriendDto();
		searchRequest.setEmail("");
		String json = toJson(searchRequest);
		mvc.perform(post("/friends/search").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(false));
	}
	
	@Test
	public void getCommonFriendList_SuccessTest() throws Exception{
		generateDataToTestCommonFriendList();
		FriendRequestDto request = new FriendRequestDto();
		List<String> frineds = new ArrayList<>();
		frineds.add("saurav@example.com");
		frineds.add("rahul@example.com");
		request.setFriends(frineds);
		String json = toJson(request);
		mvc.perform(post("/friends/common").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true))
		.andExpect(jsonPath("$.count").value(1))
		.andExpect(jsonPath("$.friends[0]").value("sachin@example.com"));
		
	}
	
	@Test
	public void subscribe_SuccessTest() throws Exception{
		UserRequestDto user = generateUserRequest("Warner", "warner@example.com");
		userService.create(user);
		user = generateUserRequest("Smith", "smith@example.com");
		userService.create(user);
		
		SubscribeRequestDto subscribe = new SubscribeRequestDto();
		subscribe.setRequestor("warner@example.com");
		subscribe.setTarget("smith@example.com");
		
		String json = toJson(subscribe);
		
		mvc.perform(post("/friends/subscribe").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
	}
	
	@Test
	public void block_SuccessTest() throws Exception{
		UserRequestDto user = generateUserRequest("Warner", "warner@example.com");
		userService.create(user);
		user = generateUserRequest("Smith", "smith@example.com");
		userService.create(user);
		
		SubscribeRequestDto subscribe = new SubscribeRequestDto();
		subscribe.setRequestor("smith@example.com");
		subscribe.setTarget("warner@example.com");
		
		String json = toJson(subscribe);
		
		mvc.perform(post("/friends/block").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
	}
	
	@Test
	public void recipients_SuccessTest() throws Exception{
		
		createRecipientData();
		UpdateRequestDto updates = new UpdateRequestDto();
		updates.setSender("virat@example.com");
		updates.setText("Hi, This is virat!! ravi@example.com");
		String json = toJson(updates);
		
		mvc.perform(post("/friends/recipients").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
	}
	
	private void createRecipientData() throws Exception {
		UserRequestDto user = generateUserRequest("virat", "virat@example.com");
		userService.create(user);
		user = generateUserRequest("dhoni", "dhoni@example.com");
		userService.create(user);
		user = generateUserRequest("amer", "amer@example.com");
		userService.create(user);
		
		FriendRequestDto request = new FriendRequestDto();
		List<String> frineds = new ArrayList<>();
		frineds.add("virat@example.com");
		frineds.add("dhoni@example.com");
		request.setFriends(frineds);
		friendService.connectFriend(request);
		
		SubscribeRequestDto subscribe = new SubscribeRequestDto();
		subscribe.setRequestor("amer@example.com");
		subscribe.setTarget("virat@example.com");
		
		String json = toJson(subscribe);
		mvc.perform(post("/friends/subscribe").content(json).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.success").value(true));
		
		
	}
	
	
	private void generateDataToTestCommonFriendList() {
		UserRequestDto user = generateUserRequest("Sachin", "sachin@example.com");
		userService.create(user);
		user = generateUserRequest("Rahul", "rahul@example.com");
		userService.create(user);
		user = generateUserRequest("Saurav", "saurav@example.com");
		userService.create(user);
		
		FriendRequestDto request = new FriendRequestDto();
		List<String> frineds = new ArrayList<>();
		frineds.add("sachin@example.com");
		frineds.add("rahul@example.com");
		request.setFriends(frineds);
		friendService.connectFriend(request);
		
		frineds.clear();
		frineds.add("sachin@example.com");
		frineds.add("saurav@example.com");
		friendService.connectFriend(request);
	}
	
	
	
	
	

}
