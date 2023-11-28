package net.javaguides.employeeservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private APIClient aPIClient;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link EmployeeServiceImpl#saveEmployee(EmployeeDto)}
     */
    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setDepartmentCode("Department Code");
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setLastName("Doe");
        employee.setOrganizationCode("Organization Code");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);
        EmployeeDto actualSaveEmployeeResult = employeeServiceImpl.saveEmployee(new EmployeeDto());
        verify(employeeRepository).save(Mockito.<Employee>any());
        assertEquals("Department Code", actualSaveEmployeeResult.getDepartmentCode());
        assertEquals("Doe", actualSaveEmployeeResult.getLastName());
        assertEquals("Jane", actualSaveEmployeeResult.getFirstName());
        assertEquals("Organization Code", actualSaveEmployeeResult.getOrganizationCode());
        assertEquals("jane.doe@example.org", actualSaveEmployeeResult.getEmail());
        assertEquals(1L, actualSaveEmployeeResult.getId().longValue());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEmployeeById() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.reactive.function.client.WebClient$RequestHeadersUriSpec.uri(String, Object[])" because the return value of "org.springframework.web.reactive.function.client.WebClient.get()" is null
        //       at net.javaguides.employeeservice.service.impl.EmployeeServiceImpl.getEmployeeById(EmployeeServiceImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        Employee employee = new Employee();
        employee.setDepartmentCode("Department Code");
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setLastName("Doe");
        employee.setOrganizationCode("Organization Code");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(null);
        employeeServiceImpl.getEmployeeById(1L);
    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#getDefaultDepartment(Long, Exception)}
     */
    @Test
    void testGetDefaultDepartment() {
        Employee employee = new Employee();
        employee.setDepartmentCode("Department Code");
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setLastName("Doe");
        employee.setOrganizationCode("Organization Code");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        APIResponseDto actualDefaultDepartment = employeeServiceImpl.getDefaultDepartment(1L, new Exception("foo"));
        verify(employeeRepository).findById(Mockito.<Long>any());
        EmployeeDto employee2 = actualDefaultDepartment.getEmployee();
        assertEquals("Department Code", employee2.getDepartmentCode());
        assertEquals("Doe", employee2.getLastName());
        assertEquals("Jane", employee2.getFirstName());
        assertEquals("Organization Code", employee2.getOrganizationCode());
        DepartmentDto department = actualDefaultDepartment.getDepartment();
        assertEquals("R&D Department", department.getDepartmentName());
        assertEquals("RD001", department.getDepartmentCode());
        assertEquals("Research and Development Department", department.getDepartmentDescription());
        assertEquals("jane.doe@example.org", employee2.getEmail());
        assertEquals(1L, employee2.getId().longValue());
    }
}
