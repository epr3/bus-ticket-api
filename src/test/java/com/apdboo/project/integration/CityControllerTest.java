package com.apdboo.project.integration;


import com.apdboo.project.controllers.CityController;
import com.apdboo.project.models.City;
import com.apdboo.project.requests.CityRequest;
import com.apdboo.project.security.CustomUserDetailsService;
import com.apdboo.project.security.jwt.JwtAuthenticationEntryPoint;
import com.apdboo.project.security.jwt.JwtTokenProvider;
import com.apdboo.project.services.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @MockBean
    CityService cityService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void it_should_return_created_city() throws Exception {
        CityRequest request = new CityRequest();
        request.setName("Bucharest");

        City city = new City();
        city.setName(request.getName());
        when(cityService.createCity(any(CityRequest.class))).thenReturn(city);

        mockMvc.perform(post("/cities")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void it_should_return_cities() throws Exception {
        mockMvc.perform(get("/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
