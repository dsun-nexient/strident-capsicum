package com.sunnyside.api.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnyside.api.config.JpaConfig;
import com.sunnyside.api.config.RootConfig;
import com.sunnyside.api.config.WebConfig;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.enumerated.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, JpaConfig.class})
public class UserControllerTestIT {
	
	@Autowired	
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private static FileWriter out;
	@Autowired
	private ObjectMapper mapper;
	private static String salt;
	
	//Test User that get deserialized from Jackson
	private User testUser;
	
	@BeforeClass
	public static void onlyOnce() throws IOException {
		File apiDoc = new File("devtool/api/documentation/api-doc.txt");
		if (apiDoc.exists()) {
			apiDoc.delete();
		}
		out = new FileWriter(apiDoc, true);
		
	}
	
	@Before
	public void init() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		testUser = freshUser();
	}
	
	private User freshUser() {
		salt = "salt";
		final User user = new User();
		user.setIsActive(true);
		user.setPassword(new Md5PasswordEncoder().encodePassword("password", salt));
		user.setRole(Role.USER);
		user.setUsername("username");
		return user;
	}
	
	@Test
	public void testList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/users")
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andDo(MockMvcResultHandlers.print(out))
				.andReturn();	
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/" + testUser.getId())
				.characterEncoding("UTF-8"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andDo(MockMvcResultHandlers.print(out))
		.andReturn();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/" + testUser.getId())
				.characterEncoding("UTF-8"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void testCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(mapper.writeValueAsString(testUser)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testUser.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.is_active").value(testUser.getIsActive()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(testUser.getPassword()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is(testUser.getRole().toString())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(testUser.getUsername()))
				.andDo(MockMvcResultHandlers.print(out))
				.andReturn();
	}
	
	
	
	
}
