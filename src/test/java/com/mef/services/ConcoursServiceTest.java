package com.mef.services;

import com.mef.Repositories.ConcoursRepository;
import com.mef.Services.ConcoursService;
import com.mef.entities.Concours;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ConcoursServiceTest {

    @Autowired
    private ConcoursService concoursService;

    @MockBean
    private ConcoursRepository concoursRepository;

    @Test
    public void getAllConcoursTest() {
        Concours concours1 = new Concours();
        Concours concours2 = new Concours();
        List<Concours> concoursList = Arrays.asList(concours1, concours2);

        when(concoursRepository.findAll()).thenReturn(concoursList);

        List<Concours> result = concoursService.getAllConcours();

        assertEquals(concoursList, result);
    }
}
