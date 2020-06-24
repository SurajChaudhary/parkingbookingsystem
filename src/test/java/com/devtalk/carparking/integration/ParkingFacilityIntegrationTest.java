package com.devtalk.carparking.integration;

import com.devtalk.carparking.model.response.FacilityResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingFacilityIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_getFacilities() throws Exception {
        // arrange - data setup


        // act
        ResponseEntity<FacilityResponse> response = restTemplate.getForEntity("/v1/facilities", FacilityResponse.class);

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(Collections.singletonList(Objects.requireNonNull(response.getBody()).getFacilities()));
        Assertions.assertThat(response.getBody().getFacilities().get(0).getName()).isEqualTo("p1");
        // response structure: //{"facilities":[{"id":101,"name":"p1","address":"testAddress"}]}
    }
}
