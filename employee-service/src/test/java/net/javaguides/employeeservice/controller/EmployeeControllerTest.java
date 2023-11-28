package net.javaguides.employeeservice.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test:  {@link EmployeeController#getEmployee(Long)}
     */
    @Test
    void testGetEmployee() throws Exception {
        when(employeeService.getEmployeeById(Mockito.<Long>any())).thenReturn(new APIResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"employee\":null,\"department\":null,\"organization\":null}"));
    }

    /**
     * Method under test:  {@link EmployeeController#getEmployee(Long)}
     */
    @Test
    void testGetEmployee2() throws Exception {
        when(employeeService.getEmployeeById(Mockito.<Long>any())).thenReturn(new APIResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/{id}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"employee\":null,\"department\":null,\"organization\":null}"));
    }

    /**
     * Method under test: {@link EmployeeController#saveEmployee(EmployeeDto)}
     */
    @Test
    void testSaveEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDepartmentCode("Department Code");
        employeeDto.setEmail("jane.doe@example.org");
        employeeDto.setFirstName("Jane");
        employeeDto.setId(1L);
        employeeDto.setLastName("Doe");
        employeeDto.setOrganizationCode("Organization Code");
        String content = (new ObjectMapper()).writeValueAsString(employeeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}
