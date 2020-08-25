package trackwell.interview.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import trackwell.interview.test.controller.VesselController;

@WebMvcTest(VesselController.class) 
public class InterviewTestApplicationTests {

	private String LEGAL_INPUT = "{\"vessel\":{\"name\":\"Titanic\",\"country\":\"Iceland\"},\"position\":{\"date\":\"2020-08-20T00:53:00.473+00:00\", \"latitude\": 20,\"longitude\": 50,\"speed\": 100}}";
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}		

	//Test successful input, check to see if response is OK, recievedDate is returned and country is not in the output
	@Test
	public void testSuccessfulVesselInsert() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/insertVessel")
				.accept(MediaType.APPLICATION_JSON)
				.content(LEGAL_INPUT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk())	
				.andExpect(content().string(containsString("\"recievedDate\"")))				
				.andExpect(jsonPath("$.vessel.country").doesNotExist())
		        .andReturn();		
	}

	//Test unsuccessful input by skipping country from the input
	@Test
	public void testUnsuccessfulVesselInsertMissingCountry() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/insertVessel")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"vessel\":{\"name\":\"Titanic\"},\"position\":{\"date\":\"2020-08-20T00:53:00.473+00:00\", \"latitude\": 20,\"longitude\": 50,\"speed\": 100}}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(request)	
				.andExpect(status().isBadRequest())	
				.andExpect(content().string(containsString("\"errors\"")))
		        .andReturn();
	}	
	
	//Test to see if speed is calculated correctly, 100 knots should be 51.4444
	@Test
	public void testSpeedCalculation() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/insertVessel")
				.accept(MediaType.APPLICATION_JSON)
				.content(LEGAL_INPUT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk())		
				.andExpect(jsonPath("$.position.speed", is(51.4)))
		        .andReturn();
	}
	
	//Test to see if location is calculated correctly, 20 degrees should be 0.3490658503988659 radians
	@Test
	public void testPositionCalculation() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/insertVessel")
				.accept(MediaType.APPLICATION_JSON)
				.content(LEGAL_INPUT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(request)
				.andExpect(status().isOk())		
				.andExpect(jsonPath("$.position.latitude", is(0.3490658503988659)))
		        .andReturn();		
	}	
}
