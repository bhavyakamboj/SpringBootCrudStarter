package com.bhavyakamboj.springbootcrudrest;

import com.bhavyakamboj.springbootcrudrest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootCrudRestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootURL(){
		return "http://localhost:" + port;
	}
	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllUsers(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootURL() + "/users", HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
	}

	@Test
	public void testGetUserByID(){
		User user = restTemplate.getForObject(getRootURL() + "/users/1", User.class);
		System.out.println(user.getFirstName());
		assertNotNull(user);
	}

	@Test
	public void testCreateUser(){
		User user = new User();
		user.setEmailId("admin@example.com");
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setCreatedBy("admin");
		user.setUpdatedBy("admin");

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootURL() + "/users", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateUser(){
		int id = 1;
		User user = restTemplate.getForObject(getRootURL() + "/users/" + id, User.class);
		user.setFirstName("admin1");
		user.setLastName("admin2");

		restTemplate.put(getRootURL() + "/users/" + id, user);

		User updatedUser = restTemplate.getForObject(getRootURL() + "/users/" + id, User.class);
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteUser(){
		int id = 2;
		User user = restTemplate.getForObject(getRootURL() + "/users/" + id, User.class);
		assertNotNull(user);

		restTemplate.delete(getRootURL() + "/users/" + id);

		try{
			user = restTemplate.getForObject(getRootURL() + "/users/" + id, User.class);
		} catch (HttpClientErrorException e){
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
