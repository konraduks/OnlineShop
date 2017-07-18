package com.konraduks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.konraduks.controller.PageController;
import com.konraduks.model.Category;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageControllerTests {

	final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private PageController controllerToTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerToTest).build();
    }	
	
	@Test
    public void getMainPageTest() throws Exception {        
		mockMvc.perform(get("/")).andExpect(status().isOk());
    }
	
	@Test
    public void getListPageTest() throws Exception {        
		mockMvc
		.perform(get("/list"))
		.andExpect(status().isOk());		
    }
	
	
	/*@Test
    public void getCategoriesPageTest() throws Exception {        
		mockMvc
		.perform(get("/categories"))
		.andExpect(status().isOk());		
    }
	
	@Test
    public void getOrdersPageTest() throws Exception {        
		mockMvc
		.perform(get("/orders"))
		.andExpect(status().isOk());		
    }
	
	@Test
    public void getCartPageTest() throws Exception {        
		mockMvc
		.perform(get("/cart"))
		.andExpect(status().isOk());		
    }
	
	@Test
    public void getMyShoppingPageTest() throws Exception {        
		mockMvc
		.perform(get("/myshopping"))
		.andExpect(status().isOk());		
    }*/
	
}
