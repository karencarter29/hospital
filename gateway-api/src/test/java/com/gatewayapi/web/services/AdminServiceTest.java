package com.gatewayapi.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayapi.model.Appointment;
import com.gatewayapi.model.Condition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class AdminServiceTest {

    @Mock
    RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    private AdminService adminService = new AdminService();

    @Test
    public void getAppointmentsTest() {
        Appointment[] appointments = {
                new Appointment(1L, 1L, Condition.RESERVED),
                new Appointment(2L, 2L, Condition.IN_PROGRESS),
                new Appointment(3L, 3L, Condition.AVAILABLE)
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(appointments);
        } catch (JsonProcessingException exc) {
            exc.printStackTrace();
        }
        Mockito
                .when(restTemplate.getForObject("http://localhost:8762/gateway/", ResponseEntity.class))
                .thenReturn(new ResponseEntity(result, HttpStatus.OK));

        String str = (String) adminService.getAllAppointments().getBody();
        assertEquals(str, result);
    }

    /*
    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        Employee emp = new Employee(“E001”, "Eric Simmons");
        Mockito
          .when(restTemplate.getForEntity(
            “http://localhost:8080/employee/E001”, Employee.class))
          .thenReturn(new ResponseEntity(emp, HttpStatus.OK));

        Employee employee = empService.getEmployee(id);
        Assert.assertEquals(emp, employee);
    }
     */
}