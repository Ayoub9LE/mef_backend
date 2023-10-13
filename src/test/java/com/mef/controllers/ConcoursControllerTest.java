package com.mef.controllers;

import com.mef.Services.ConcoursService;
import com.mef.entities.Concours;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConcoursControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcoursService concoursService;

    @Test
    public void getAllConcoursTest() throws Exception {
        Concours concours1 = new Concours();
        Concours concours2 = new Concours();
        List<Concours> concoursList = Arrays.asList(concours1, concours2);

        when(concoursService.getAllConcours()).thenReturn(concoursList);

        mockMvc.perform(get("/api/concours/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
