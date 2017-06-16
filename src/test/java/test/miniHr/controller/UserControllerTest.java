package test.miniHr.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.miniHr.base.BaseTest;

import static org.junit.Assert.*;

/**
 * Created by DELL on 2017/6/16.
 */
public class UserControllerTest extends BaseTest{

    @Test
    public void update() throws Exception {
        String uri = "/user/update";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("openId","oCeMZ0WUojqOrmtZOA0f1-8qvJg0")
                .param("name","test")
                .param("phone","13312345678")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);

    }
}