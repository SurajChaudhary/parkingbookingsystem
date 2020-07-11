package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.request.CityRequest;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@WebMvcTest(SeedDataController.class)
@Disabled
class SeedDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeedDataService seedDataService;

    @Test
    @Disabled
    void test_addStates() throws Exception {
        String url = "/v1/states";

        doNothing().when(seedDataService).addNewStatesToSystem(Mockito.anyList());
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Arrays.asList("Delhi")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Disabled
    void getStates() throws Exception {
        String url = "/v1/states";
        List<State> states = Collections.singletonList(new State(1, 1, "Delhi", Collections.emptyList()));
        given(seedDataService.getStates()).willReturn(states);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Collections.singletonList(new State(1, 1, "Delhi", Collections.emptyList())));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Disabled
    void test_addCities() throws Exception {
        String url = "/v1/cities";

        doNothing().when(seedDataService).addNewCitiesToSystem(Mockito.anyList());
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Collections.singletonList(new CityRequest(1, "New Delhi", 1))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @Disabled
    void getCities() throws Exception {
        String url = "/v1/cities/{cityName}";
        City city = new City(1,"New Delhi",1);
        given(seedDataService.getCityDetailsByName(Mockito.anyString())).willReturn(city);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url, "Delhi")).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(new City(1,"New Delhi",1));
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