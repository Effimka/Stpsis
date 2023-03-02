package com.example.demo.Controller;

import com.example.demo.Types.createEmploeeTemplate;
import com.example.demo.Types.quizeTemplate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.Lib.EmploeeService;
import com.example.demo.Models.Emploee;
import com.example.demo.Types.loginTemplate;
import com.example.demo.repo.EmploeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
class QuizeTest {

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmploeeRepo repo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void init() throws Exception {
        mockMvc.perform(get("/quize"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void passQuize()throws Exception {
        quizeTemplate template = new quizeTemplate();
        template.id = 1L;
        template.bio = 12;
        template.moral = 23;
        template.psy = 13;
        template.intel = 11;
        template.unreab = 32;

        Mockito.when(repo.save(Mockito.any())).thenReturn(template);
        mockMvc.perform(
                        post("/quize/pass")
                                .content(objectMapper.writeValueAsString(template))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}