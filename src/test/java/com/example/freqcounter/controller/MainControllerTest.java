package com.example.freqcounter.controller;

import com.example.freqcounter.dto.Request;
import com.example.freqcounter.service.MainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MainService mainService;

    @Test
    public void countFrequencyWhenTextIsValidReturnOk() throws Exception {
        String jsonRequest = """
                        {
                            "text":"eeeeeaabbceecdddd"
                        }
                """;

        Map<String, Long> response = Map.ofEntries(
                Map.entry("e", 7L),
                Map.entry("d", 4L),
                Map.entry("a", 2L),
                Map.entry("b", 2L),
                Map.entry("c", 2L));

        String jsonResponse = objectMapper.writeValueAsString(response);

        when(mainService.countFrequency(any(Request.class))).thenReturn(response);
        this.mockMvc.perform(post("/")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

        verify(mainService).countFrequency(any(Request.class));
        verifyNoMoreInteractions(mainService);
    }

    @Test
    public void countFrequencyWhenTextIsEmptyReturnBadRequest() throws Exception {
        String jsonRequest = """
                {
                    "text":""
                }              
                """;

        String jsonResponse = """
                    {
                        "text": "must not be empty" 
                    }
                """;
        this.mockMvc.perform(post("/")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(mainService);

    }

    @Test
    public void countFrequencyWhenRequestIsNullReturnBadRequest() throws Exception {
        String jsonRequest = """             
                """;

        String jsonResponse = "Internal server error!";
        this.mockMvc.perform(post("/")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(jsonResponse));

        verifyNoInteractions(mainService);
    }

    @Test
    public void countFrequencyWhenTextIsAbsentReturnBadRequest() throws Exception {
        String jsonRequest = """
                {
                }                
                """;

        String jsonResponse = """
                    {
                        "text": "must not be empty"   
                    }
                """;
        this.mockMvc.perform(post("/")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(mainService);
    }

    @Test
    public void countFrequencyWhenTextIsNullReturnBadRequest() throws Exception {

        String jsonRequest =
                """
                            {
                            "text":null
                            }                
                        """;

        String jsonResponse = """
                    {
                        "text": "must not be empty"   
                    }
                """;
        this.mockMvc.perform(post("/")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(mainService);
    }
}
