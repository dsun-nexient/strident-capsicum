package com.sunnyside.api.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnyside.api.config.JpaConfig;
import com.sunnyside.api.config.RootConfig;
import com.sunnyside.api.config.WebConfig;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.enumerated.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class, WebConfig.class, JpaConfig.class })
//@Rollback(true)
public class UserControllerTestIT {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private Md5PasswordEncoder encoder;
	@Autowired
	@Qualifier("salt")
	private String salt;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private static FileWriter out;

	private User testUser;
	private final int USER_ID = 0;

	/**
	 * Before the integration test setup the file output stream
	 * 
	 * @throws IOException
	 */
	@BeforeClass
	public static void onlyOnce() throws IOException {
		File apiDoc = new File("devtool/api/documentation/api-doc.txt");
		if (apiDoc.exists()) {
			apiDoc.delete();
		}
		out = new FileWriter(apiDoc, true);

	}

	/**
	 * Build the mock mvc test with the web application context. Get a fresh
	 * user instance
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		testUser = freshUser();
	}

	private User freshUser() {
		final User user = new User();
		user.setIsActive(true);
		user.setPassword("password");
		user.setRole(Role.USER);
		user.setUsername("username");
		return user;
	}

	private ResultActions performPostRequest() throws Exception {
		return mockMvc
				.perform(post("/v1/users")
				.content(mapper.writeValueAsString(testUser))
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON));
	}
	
	private ResultActions performGetRequest() throws Exception {
		return mockMvc
				.perform(get("/v1/users")
				.accept(APPLICATION_JSON));
	}
	
	@SuppressWarnings("unused")
	private ResultActions performGetRequestWithPathParam(final Integer userId) throws Exception {
		return mockMvc
				.perform(get("/v1/users/{userId}", USER_ID)
				.accept(APPLICATION_JSON));
	}

	@Test
	@Transactional
	public void testCreate() throws Exception {
		performPostRequest()
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(any(int.class)))
			.andExpect(jsonPath("$.is_active").value(is(true)))
			.andExpect(jsonPath("$.username").value(is(testUser.getUsername())))
			.andExpect(jsonPath("$.password").value(
					is(encoder.encodePassword(testUser.getPassword(), salt))
					))
			.andExpect(jsonPath("$.role").value(is(testUser.getRole().toString())))
			.andDo(print(out));
	}
	
	
	
	@Test
	public void testList() throws Exception {
		performGetRequest()
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].id").value(any(int.class)))
			.andExpect(jsonPath("$.[0].is_active").value(any(boolean.class)))
			.andExpect(jsonPath("$.[0].username").value(any(String.class)))
			.andExpect(jsonPath("$.[0].password").value(any(String.class)))
			.andExpect(jsonPath("$.[0].role").value(containsInAnyOrder(Role.ADMIN.toString(), Role.USER.toString())))
			.andDo(print(out));
			
	}

}
