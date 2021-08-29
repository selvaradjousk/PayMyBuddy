package com.paymybuddy.webapp.unitTest.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// WebMvcTest(controllers = HelloController.class) - load only the controller by listing here
@RunWith(SpringRunner.class)
@WebMvcTest
class HomeControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShouldReturnMessage() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.status().is(200))
    .andExpect(MockMvcResultMatchers.content().string("login"))
    .andExpect(MockMvcResultMatchers.header().string("Content-Type", "text/plain;charset=UTF-8"))
    .andExpect(MockMvcResultMatchers.header().string("Content-Length", "5"));
}
}