package itst.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



// It does not start the server at all but to test only the layer below that, where Spring handles the incoming HTTP request and hands it off to your controller
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = itst.example.ExampleApplication.class)
public class StudentControllerTestSP {

    @Autowired
	private MockMvc mvc;

	@Test
	public void getAllTest() throws Exception {
		mvc.perform(get("http://localhost:8080/api/v1/students").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
	}
    
    @Test
	public void getByControlNumberTest() throws Exception {
		mvc.perform(get("http://localhost:8080/api/v1/students/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['Control number']]", is(2)));
	}

    @Test
	public void getByControlNumberNotFoundTest() throws Exception {
		mvc.perform(get("http://localhost:8080/api/v1/students/21212333").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
	}
}
