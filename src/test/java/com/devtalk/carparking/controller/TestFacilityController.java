package com.devtalk.carparking.controller;

import com.devtalk.carparking.configuration.auth.ApplicationUserService;
import com.devtalk.carparking.configuration.jwt.JwtConfig;
import com.devtalk.carparking.configuration.jwt.JwtSecretKey;
import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.service.FacilityService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(FacilityController.class)
public class TestFacilityController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  ApplicationUserService userDetailsService;
    @MockBean
    private  PasswordEncoder passwordEncoder;
    @MockBean
    private  JwtConfig jwtConfig;
    @MockBean
    private  JwtSecretKey jwtSecretKey;


    @MockBean
    private FacilityService facilityService;


    @Test
    @WithMockUser(username = "admin", authorities = {"customer:read"})
    public void test_getFacilities_givingFacilities() throws Exception {
        // arrange
        String url = "/v1/facilities";
        List<Facility> facilities = getResponse();
        given(facilityService.getFacilities()).willReturn(facilities);

        // act
        mockMvc.perform(MockMvcRequestBuilders.get(url))

                // assertion
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("facilities").isNotEmpty());
        //Todo: verifying internal properties like id and name using jsonPath
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"customer:read"})
    public void test_getFacilities_usingMvcResult_givingFacilities() throws Exception {
        // arrange
        String url = "/v1/facilities";
        List<Facility> facilities = getResponse();
        given(facilityService.getFacilities()).willReturn(facilities);

        // act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // assertion
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = "{\"facilities\":[{\"id\":101,\"name\":\"p1\",\"address\":\"testAddress\"}]}";
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"customer:read"})
    public void test_getFacilities_givingNotFoundException() throws Exception {
        // arrange
        String url = "/v1/facilities";
        given(facilityService.getFacilities()).willReturn(null);

        // act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // assertion
        Assertions.assertEquals(FacilityNotFoundException.class, Objects.requireNonNull(mvcResult.getResolvedException()).getClass());
    }


    //Todo: Need to check why @Test(expected=..) is not working
   /* @Test(expected = FacilityNotFoundException.class)
    public void test_getFacilities_usingExpected_givingNotFoundException() throws Exception {
        // arrange
        String url = "/v1/facilities";
        given(facilityService.getFacilities()).willReturn(null);
        // act
        mockMvc.perform(MockMvcRequestBuilders.get(url));
    }*/

    private List<Facility> getResponse() {
        List<Facility> facilities = new ArrayList<>();
        Facility facility = new Facility();
        facility.setId(101);
        facility.setName("p1");
        facility.setAddress("testAddress");
        facilities.add(facility);
        return facilities;
    }
}
