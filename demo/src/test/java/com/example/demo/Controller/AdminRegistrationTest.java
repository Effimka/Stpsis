package com.example.demo.Controller;

import com.example.demo.Lib.EmploeeService;
import com.example.demo.Models.Emploee;
import com.example.demo.Models.Report;
import com.example.demo.Types.createEmploeeTemplate;
import com.example.demo.Types.loginTemplate;
import com.example.demo.repo.EmploeeRepo;
import com.example.demo.repo.ReportRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminRegistrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmploeeRepo repo;
    @MockBean
    private ReportRepo repoReport;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmploeeService emploeeService;

    @Test
    void init() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void users() throws Exception {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Emploee("Rare", "1111", "Far", "ala"),
                new Emploee("Alla", "2131", "ada", "aw23")
        ));
        mockMvc.perform(get("/admin/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {
        Emploee emp = new Emploee("Rare", "1111", "Far", "ala");
        when(repo.findById(anyLong())).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/admin/user/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void makeEmploee() throws Exception {
        createEmploeeTemplate template = new createEmploeeTemplate();
        template.login = "fre";
        template.password = "1111";
        template.name = "Jane";
        template.lastname = "Jae";
        Mockito.when(repo.save(Mockito.any())).thenReturn(template);
        mockMvc.perform(
                        post("/admin/makeEmpl")
                                .content(objectMapper.writeValueAsString(template))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void reports() throws Exception {
        mockMvc.perform(get("/admin/reports"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getReport() throws Exception {
        when(repoReport.findById(anyLong())).thenReturn(Optional.of(new Report()));
        mockMvc.perform(get("/admin/report/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void makeReport() throws Exception {
        mockMvc.perform(get("/admin/makeReport"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmploee() throws Exception {
        Emploee emp = new Emploee("Jane","fre", "fre","fre");
        Mockito.when(repo.findById(Mockito.any())).thenReturn(Optional.of(emp));
        mockMvc.perform(
                        delete("/admin/user/remove/2"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}