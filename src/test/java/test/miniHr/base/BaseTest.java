package test.miniHr.base;

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
import test.miniHr.controller.ResumeControllerTest;

/**
 * Created by DELL on 2017/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MiniHrBootStrap.class)
@WebAppConfiguration
public abstract class BaseTest {

    public static Logger log = LoggerFactory.getLogger(ResumeControllerTest.class);

    public MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ResumeService resumeServiceImpl;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}