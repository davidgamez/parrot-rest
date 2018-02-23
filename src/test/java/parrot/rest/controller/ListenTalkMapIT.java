/**
 * 
 */
package parrot.rest.controller;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import parrot.rest.BaseIntegrationController;
import parrot.rest.common.Phrase;

/**
 *
 */
@PropertySource("classpath:application-test-map.properties")
public class ListenTalkMapIT extends BaseIntegrationController {

  private static final String JSON_RESPONSE = "{\"id\": 1, \"name\": \"Test response\"}";
  
  @Autowired
  ListenController listenController;

  @Autowired
  WebApplicationContext context;
  
  @Before
  public void setup() {
    initMocks(this);
    this.mockMvc = webAppContextSetup(context)
        .alwaysDo(print()).build();
  }
  
  @After
  public void validate() {
    Mockito.validateMockitoUsage();
  }
  
  @Test
  public void testListenTalkBasic() throws Exception {
    
    Phrase phrase = new Phrase();
    phrase.setAppContext("app_context");
    phrase.setUrl("the_url");
    phrase.setResponse(JSON_RESPONSE);
    
    Gson gson = new Gson();
    String json = gson.toJson(phrase);
    
//    Listen
    mockMvc.perform(post("/listen").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk())
      .andExpect(content().string("OK"));

//    Talk
    mockMvc.perform(get("/talk/app_context/the_url"))
    .andExpect(status().isOk())
  .andExpect(content().string(JSON_RESPONSE));    
  }

  @Test
  public void testListenTalkParameters() throws Exception {
    
    Phrase phrase = new Phrase();
    phrase.setAppContext("app_context");
    phrase.setUrl("the_url?param=true");
    phrase.setResponse(JSON_RESPONSE);
    
    Gson gson = new Gson();
    String json = gson.toJson(phrase);
    
//    Listen
    mockMvc.perform(post("/listen").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk())
      .andExpect(content().string("OK"));

//    Talk
    mockMvc.perform(get("/talk/app_context/the_url?param=true"))
    .andExpect(status().isOk())
  .andExpect(content().string(JSON_RESPONSE));    
  }
  
  @Test
  public void testListenMissingUrl() throws Exception {

//    Talk
    mockMvc.perform(get("/talk/no_existing_app_context/the_url"))
    .andExpect(status().isNotFound());    
  }
}
