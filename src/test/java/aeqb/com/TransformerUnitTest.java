package aeqb.com;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import aeqb.com.beans.Transformer;

@WebAppConfiguration 
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformerUnitTest extends TransformerApplicationTests {

	 	@Autowired
	    private WebApplicationContext webApplicationContext;

	    private MockMvc mockMvc;

	    @Before
	    public void setup() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                .build();
	        
	        System.out.println("******Junit test------------------------------------------******************-");
	    }

	    private void addTransformer(Transformer anObject) throws Exception{	    	       
	 	   
	        Gson gson = new Gson();
	        String json = gson.toJson(anObject);

	        MvcResult result = this.mockMvc.perform(post("http://localhost:8080/transformer")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andReturn();
	        System.out.println("******add return :"+result.getResponse().getContentAsString());
	        
	        outTransformer();
	    }
	    
	    private void updateTransformer(Transformer anObject) throws Exception{	    	       
		 	   
	        Gson gson = new Gson();
	        String json = gson.toJson(anObject);

	        MvcResult result = this.mockMvc.perform(put("http://localhost:8080/transformer/0")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andReturn();
	        System.out.println("******update return :"+result.getResponse().getContentAsString());
	        
	        outTransformer();
	    }
	    
	    private void outTransformer() throws Exception{	    
	    	MvcResult result = this.mockMvc.perform(get("http://localhost:8080/transformer")).andReturn();
	        System.out.println("******List return :"+result.getResponse().getContentAsString());
	    }
	    
	    @Test
	    public void validate_get_transformer() throws Exception {	 
	    	// add
	    	addTransformer(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,10));
	    	
	     	//modify
	    	updateTransformer(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,7));
	    	
	    	//delete
	    	MvcResult result = this.mockMvc.perform(get("http://localhost:8080/transformer/0")).andReturn();
	        System.out.println("******Delete return :"+result.getResponse().getContentAsString());
	        
	    	//get
	        outTransformer();		
	        
	        addTransformer(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,10));
	    	addTransformer(new Transformer("Bluestreak","A", 6,6,7,9,5,2,9,7));
	    	addTransformer(new Transformer("Hubcap", "A", 4,4,4,4,4,4,4,4));
	    	
	     	//get
	        outTransformer();
	       
	    	MvcResult resultWin = this.mockMvc.perform(get("http://localhost:8080/transformer/winningteam")).andReturn();
	    	 System.out.println("******Winteam return :"+resultWin.getResponse().getContentAsString());
	    	 
	    	 System.out.println("******Junit test end--------------******************-");
	    }
}
