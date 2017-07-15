package test.miniHr.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.miniHr.base.BaseTest;

/**
 * Created by DELL on 2017/6/12.
 */
public class ResumeControllerTest extends BaseTest {

    @Test
    public void resume() throws Exception {
        String uri = "/resume/insert";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("openId","11")
                .param("jobId","2")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }

    @Test
    public void queryResume() throws Exception {
        String uri = "/resume/query";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("openId","oCeMZ0dLSFLOSvLj55reMjBVsP64")
                .param("level","2")
                .param("state", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }

    @Test
    public void saveResume() throws Exception {
        String uri = "/resume/save";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }

    @Test
    public void deleteResume() throws Exception {
        String uri = "/resume/delete";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        log.info("status = {}, content = {}", status, content);
    }
}