package com.devtalk.carparking.controller;

import com.devtalk.carparking.configuration.auth.ApplicationUserService;
import com.devtalk.carparking.configuration.jwt.JwtConfig;
import com.devtalk.carparking.configuration.jwt.JwtSecretKey;
import com.devtalk.carparking.model.request.CityRequest;
import com.devtalk.carparking.model.request.StateRequest;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(SeedDataController.class)
class SeedDataControllerTest {

    @MockBean
    private ApplicationUserService userDetailsService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtConfig jwtConfig;
    @MockBean
    private JwtSecretKey jwtSecretKey;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeedDataService seedDataService;

    @Test
    @WithMockUser(username = "admin", authorities = {"admin:write"})
    void test_addStates() throws Exception {
        String url = "/api/v1/admin/states";

        StateRequest stateRequest = new StateRequest(1, "Delhi", Collections.emptyList());
        given(seedDataService.addNewStatesToSystem(Arrays.asList(stateRequest))).willReturn(Arrays.asList("Delhi"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Arrays.asList(stateRequest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Arrays.asList("Delhi"));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getStates() throws Exception {
        String url = "/api/v1/admin/states";
        List<State> states = Collections.singletonList(new State(1, 1, "Delhi", Collections.emptyList()));
        given(seedDataService.getStates()).willReturn(states);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Collections.singletonList(new State(1, 1, "Delhi", Collections.emptyList())));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"admin:write"})
    void test_addCities() throws Exception {
        String url = "/api/v1/admin/cities";
        CityRequest cityRequest = new CityRequest(1, "New Delhi");
        given(seedDataService.addNewCitiesToSystem(Arrays.asList(cityRequest))).willReturn(Arrays.asList("New Delhi"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(Collections.singletonList(new CityRequest(1, "New Delhi"))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(Arrays.asList("New Delhi"));
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getCities() throws Exception {
        String url = "/api/v1/admin/cities/{cityName}";
        City city = new City(1, "New Delhi", 1);
        given(seedDataService.getCityDetailsByName(Mockito.anyString())).willReturn(city);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url, "Delhi")).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = asJsonString(new City(1, "New Delhi", 1));
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