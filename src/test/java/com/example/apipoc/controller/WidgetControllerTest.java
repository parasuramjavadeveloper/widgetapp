package com.example.apipoc.controller;

import com.example.apipoc.entity.Widget;
import com.example.apipoc.service.WidgetService;
import com.example.apipoc.utils.MockUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Controller to test all widget services
 * @author Parasuram
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WidgetControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private WidgetService widgetService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Test to get all widgets service call
     */
    @Test
    public void testWidgets() throws Exception {
        when(widgetService.getAllWidgets()).thenReturn(MockUtil.widgets());
        MvcResult mvcResult = mockMvc
                .perform(get("http://localhost:8055/api/widget")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].id").value("1"))
                .andExpect(jsonPath("[0].xAxis").value("10"))
                .andExpect(jsonPath("[0].yAxis").value("5"))
                .andExpect(jsonPath("[0].zIndex").value("1"))
                .andExpect(jsonPath("[0].width").value("50"))
                .andExpect(jsonPath("[0].height").value("30"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), 200);
    }

    /**
     * Test to create widget service call
     */
    @Test
    public void createWidget() throws Exception {
        Widget widget = MockUtil.mockWidget();
        when(widgetService.createWidget(widget)).thenReturn(MockUtil.widgets());
        MvcResult mvcResult = mockMvc
                .perform(post("http://localhost:8055/api/widget")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(widget))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].id").value("1"))
                .andExpect(jsonPath("[0].xAxis").value("10"))
                .andExpect(jsonPath("[0].yAxis").value("5"))
                .andExpect(jsonPath("[0].zIndex").value("1"))
                .andExpect(jsonPath("[0].width").value("50"))
                .andExpect(jsonPath("[0].height").value("30"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), 200);
    }

    /**
     * Test to get widget by id service call
     */
    @Test
    public void getWidgetById() throws Exception {
        when(widgetService.getWidget(1)).thenReturn(Optional.ofNullable(MockUtil.mockWidget()));
        MvcResult mvcResult = mockMvc
                .perform(get("http://localhost:8055/api/widget/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("xAxis").value("3"))
                .andExpect(jsonPath("yAxis").value("2"))
                .andExpect(jsonPath("zIndex").value("2"))
                .andExpect(jsonPath("width").value("3"))
                .andExpect(jsonPath("height").value("5"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), 200);
    }

    /**
     * Test to delete widget by id service call
     */
    @Test
    public void deleteWidgetById() throws Exception {
        when(widgetService.getWidget(2)).thenReturn(Optional.of(new Widget()));
        MvcResult mvcResult = mockMvc
                .perform(delete("http://localhost:8055/api/widget/2")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getContentAsString(), "Widget Successfully Deleted");
        Assert.assertEquals(response.getStatus(), 200);
    }

    /**
     * Test to delete widget by id service call
     */
    @Test
    public void updateWidgetById() throws Exception {
        Widget widget =  MockUtil.mockWidget();
        when(widgetService.updateWidget(1,widget)).thenReturn(MockUtil.widgets());
        MvcResult mvcResult = mockMvc
                .perform(put("http://localhost:8055/api/widget/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(widget))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].id").value("1"))
                .andExpect(jsonPath("[0].xAxis").value("10"))
                .andExpect(jsonPath("[0].yAxis").value("5"))
                .andExpect(jsonPath("[0].zIndex").value("1"))
                .andExpect(jsonPath("[0].width").value("50"))
                .andExpect(jsonPath("[0].height").value("30"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), 200);
    }
}
