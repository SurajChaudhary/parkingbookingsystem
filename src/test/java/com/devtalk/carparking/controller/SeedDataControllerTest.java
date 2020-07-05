package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.service.FacilityService;
import com.devtalk.carparking.service.SeedDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@WebMvcTest(SeedDataController.class)
class SeedDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeedDataService seedDataService;

    @Test
    void test_addStates() throws Exception{
        String url = "/v1/states";

        doNothing().when(seedDataService).addStates(Mockito.anyList());
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Arrays.asList("Delhi")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getStates() throws Exception{
        String url = "/v1/states";
        List<String> states = Arrays.asList("Delhi");
        given(seedDataService.getStates()).willReturn(states);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Arrays.asList("Delhi"));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void test_addCities() throws Exception {
        String url = "/v1/cities";

        doNothing().when(seedDataService).addCities(Mockito.anyList());
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Arrays.asList(new City(1,"New Delhi","Delhi"))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void getCities() throws Exception{
        String url = "/v1/cities/{stateName}";
        List<String> cities = Arrays.asList("New Delhi");
        given(seedDataService.getCitiesByStateName(Mockito.anyString())).willReturn(cities);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url,"Delhi")).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Arrays.asList("New Delhi"));
        Assertions.assertEquals(expectedResponse, response);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}