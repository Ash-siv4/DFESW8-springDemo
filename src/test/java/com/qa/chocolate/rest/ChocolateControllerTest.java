package com.qa.chocolate.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.chocolate.domain.Chocolate;

@SpringBootTest
@AutoConfigureMockMvc // acts like your postman - makes the test requests
@Sql(scripts = {"classpath:chocolate-schema.sql","classpath:chocolate-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)//pre-populate the database
@ActiveProfiles("test")
public class ChocolateControllerTest {
	
	@Autowired
	private MockMvc mock;//TO MOCK THE REQUESTS
	
	@Autowired
	private ObjectMapper map;//INTERPRETS JSON
	
	@Test
	void testCreateCont() throws Exception {
		//----request
		//CREATE A CHOCOLATE
		Chocolate newC = new Chocolate("AERO", "Cadbury", "Milk", 89, "Bubbles", 50);
		//CONVERT INTO JSON string
		String newCJSON = this.map.writeValueAsString(newC);
		//Build the mock request
		RequestBuilder mockRequest = post("/createChoco").contentType(MediaType.APPLICATION_JSON).content(newCJSON);
		
		///-----response
		Chocolate savedC = new Chocolate(2L,"AERO", "Cadbury", "Milk", 89, "Bubbles", 50);//already inserted one record on line 21
		//convert to JSON
		String savedCJSON = this.map.writeValueAsString(savedC);
		
		//---test response (status + body)
		//test status = 201-CREATED
		ResultMatcher matchStatus = status().isCreated();
		//test response body
		ResultMatcher matchBody = content().json(savedCJSON);
		
		//perfom the test
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		
		
	}
	
}
