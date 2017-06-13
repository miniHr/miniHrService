package test.miniHr.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.miniHr.base.BaseTest;

/**
 * Created by DELL on 2017/6/13.
 */
public class CompanyControllerTest extends BaseTest {

    @Test
    public void queryJob() throws Exception {

    }

    @Test
    public void addCompanyInfo() throws Exception {

    }

    @Test
    public void queryCompanyInfo() throws Exception {
        String uri = "/company/query";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("id","23")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }
}