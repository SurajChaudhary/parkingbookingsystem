package com.devtalk.carparking.controller;

import com.devtalk.carparking.dataaccess.repository.UserInfoDetailsRepository;
import com.devtalk.carparking.service.UserInfoDetailsService;
import com.devtalk.carparking.service.impl.UserInfoInfoDetailsServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserInfoDetailsController {

   /* @Autowired
    private MockMvc mvc;

    @MockBean
    private UserInfoDetailsRepository userInfoDetailsRepository;
    @InjectMocks
    private final UserInfoDetailsService service = new UserInfoInfoDetailsServiceImpl(userInfoDetailsRepository);

    private final UserInfoDetailsController userInfoDetailsController = new UserInfoDetailsController(service);


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userInfoDetailsController).build();
    }*/

   /* @Test
    public void getAllUsersAPI() throws Exception {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserName("Test Employee");
        userList.add(user);
        Optional<List<User>> response = Optional.of(userList);
        //Mockito.when(userDetailsRepository.findAll()).thenReturn(userList);
        Mockito.when(service.getAllUsers()).thenReturn(response);
        mvc.perform( MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.users").exists())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.users[*].userName").isNotEmpty());
    }*/
}
