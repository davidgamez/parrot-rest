/**
 * 
 */
package parrot.rest.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import parrot.rest.ParrotRestApplication;
import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ParrotRestApplication.class)
@ContextConfiguration(classes = { ApplicationTestConfiguration.class })
public class ParrotRestDocumentationTest {

	@Rule
	public JUnitRestDocumentation restDoc = new JUnitRestDocumentation("target/snippets");

	private static final String JSON_RESPONSE = "{\"id\": 1, \"name\": \"Test response\"}";
	
	private RestDocumentationResultHandler document;
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
    private ObjectMapper objectMapper;
	
	@Mock
	JedisConnectionFactory JedisConnectionFactory;
	
	@Before
	public void setUp() {
		this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDoc))
				.alwaysDo(this.document).build();
	}

    @Test
    public void listenExample() throws Exception {
    		Phrase phrase = new Phrase();
    		phrase.setAppContext("appContext");
    		phrase.setResponse(JSON_RESPONSE);
    		phrase.setUrl("path");
    		
        this.mockMvc.perform(post("/listen").contentType(MediaTypes.HAL_JSON).content(this.objectMapper.writeValueAsString(phrase)))
        		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString().equals("OK");
        
    }

    @Test
    public void talkExample() throws Exception {
        this.mockMvc.perform(get("/talk/appContext/path"))
        		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString().equals(JSON_RESPONSE);
    }
    
    @Test
    public void healthExample() throws Exception {
        this.mockMvc.perform(get("/health"))
        		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString().equals("I'm a healthy parrot, do you have a sunflower seeds for me?");
    }
}
