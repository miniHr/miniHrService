package test.miniHr.controller;

import com.miniHr.MiniHrBootStrap;
import com.miniHr.service.ResumeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by DELL on 2017/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MiniHrBootStrap.class)
@WebAppConfiguration
public class ResumeControllerTest {

    private static Logger log = LoggerFactory.getLogger(ResumeControllerTest.class);

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ResumeService resumeServiceImpl;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void resume() throws Exception {
        String uri = "/resume/insert";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("openId","11")
                .param("jobId","1").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }
}