package com.example.rockstars;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.rockstars.service.RockstarsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class RockstarsApplicationTests {
		
	@MockBean
	RockstarsService rockstarsServiceMock;

    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8080/rockstars";

	@Test	
	public void checkAllRockstarsOnGenreMetal() throws Exception {		
		    	     
	    baseUrl = baseUrl + "/genre/Metal";	 
	    
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);    	
	    
	    Assert.assertEquals(200, result.getStatusCodeValue());	 
	    Assert.assertNotEquals(true, result.getBody().contains("Pop-Rock"));
	    Assert.assertEquals(true, result.getBody().contains("Metal"));
	}
	
	@Test	
	public void checkArtistById() throws Exception {		
		    
	    baseUrl = baseUrl+ "/2621";

	    URI uri = new URI(baseUrl);	 	   
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertNotEquals(true, result.getBody().contains("1980"));
	    Assert.assertEquals(true, result.getBody().contains("Sister Sledge"));
	}

}
 