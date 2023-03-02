package com.example.demo.Controller;

import com.example.demo.Lib.EmploeeService;
import com.example.demo.Models.Emploee;
import com.example.demo.Types.loginTemplate;
import com.example.demo.repo.EmploeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginTest {

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmploeeRepo repo;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmploeeService emploeeService;

    @Test
    void init() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        loginTemplate lT = new loginTemplate();
        lT.login = "Rare";
        lT.password = "1111";

        when(repo.findAll()).thenReturn(Arrays.asList(
                new Emploee("Rare", "1111", "Far", "ala"),
                new Emploee("Alla", "2131", "ada", "aw23")
        ));


        mockMvc.perform(post("/login/entred")
                        .content(objectMapper.writeValueAsString(lT))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$", hasSize(2)))

    }
}