package com.devtalk.carparking.controller;

import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.service.FacilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(FacilityController.class)
public class TestFacilityController {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private FacilityController facilityController;

    @Test
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
    }

    @Test(expected = FacilityNotFoundException.class)
    public void test_getFacilities_givingNotFoundException() throws Exception {
        // arrange
        String url = "/v1/facilities";
        given(facilityService.getFacilities()).willThrow(new FacilityNotFoundException());

        // act
        mockMvc.perform(MockMvcRequestBuilders.get(url))

                // assertion
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("facilities").isNotEmpty());

    }

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
