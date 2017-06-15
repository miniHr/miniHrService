package test.miniHr.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.miniHr.base.BaseTest;

import static org.junit.Assert.*;

/**
 * Created by DELL on 2017/6/15.
 */
public class JobControllerTest extends BaseTest {

    @Test
    public void recommend() throws Exception {
        String uri = "/job/recommend";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("openId","oCeMZ0WUojqOrmtZOA0f1-8qvJg0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }

    @Test
    public void queryJobInfo() throws Exception {

    }
}