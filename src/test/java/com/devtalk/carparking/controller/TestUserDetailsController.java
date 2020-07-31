package com.devtalk.carparking.controller;

import com.devtalk.carparking.configuration.auth.ApplicationUserService;
import com.devtalk.carparking.configuration.jwt.JwtConfig;
import com.devtalk.carparking.configuration.jwt.JwtSecretKey;
import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

import static com.devtalk.carparking.controller.SeedDataControllerTest.asJsonString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UserDetailsController.class)
class TestUserDetailsController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationUserService userDetailsService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtConfig jwtConfig;
    @MockBean
    private JwtSecretKey jwtSecretKey;

    @MockBean
    private UserInfoDetailsService userInfoDetailsService;

    @BeforeEach
    public void setup() throws Exception {
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"CUSTOMER", "ADMIN", "ADMIN_TRAINEE", "customer:read"})
    public void test_createUserDetails_givingCreatedResponse() throws Exception {
        // arrange
        String url = "/api/v1/userDetails";
        UserInfoDetails userInfoObject = getUserInfoObject("Test User", "user.test@bookmyslot.com");
        given(userInfoDetailsService.createUser(userInfoObject)).willReturn("Test User");

        // act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(asJsonString(userInfoObject))
                //.header(HttpHeaders.AUTHORIZATION, "Bearer 123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();


        // assertion
        String response = mvcResult.getResponse().getContentAsString();
        String expectedResponse = "Test User";
        Assertions.assertEquals(expectedResponse, response);
        //Todo: verifying internal properties like id and name using jsonPath
    }

    private UserInfoDetails getUserInfoObject(String userName, String userEmail) {
        String[] names = userName.split(" ");
        return new UserInfoDetails(
                userName,
                userEmail,
                1234567890,
                names[0],
                names[1],
                "test address",
                "test longitude",
                "test latitude",
                "DL123456DGV",
                "AA1234GHF",
                null,
                null,
                null
        );
    }

}