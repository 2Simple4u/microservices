package net.javaguides.employeeservice.dto;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class APIResponseDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link APIResponseDto#APIResponseDto()}
     *   <li>{@link APIResponseDto#setDepartment(DepartmentDto)}
     *   <li>{@link APIResponseDto#setEmployee(EmployeeDto)}
     *   <li>{@link APIResponseDto#setOrganization(OrganizationDto)}
     *   <li>{@link APIResponseDto#getDepartment()}
     *   <li>{@link APIResponseDto#getEmployee()}
     *   <li>{@link APIResponseDto#getOrganization()}
     * </ul>
     */
    @Test
    void testConstructor() {
        APIResponseDto actualApiResponseDto = new APIResponseDto();
        DepartmentDto department = new DepartmentDto();
        actualApiResponseDto.setDepartment(department);
        EmployeeDto employee = new EmployeeDto();
        actualApiResponseDto.setEmployee(employee);
        OrganizationDto organization = new OrganizationDto();
        actualApiResponseDto.setOrganization(organization);
        DepartmentDto actualDepartment = actualApiResponseDto.getDepartment();
        EmployeeDto actualEmployee = actualApiResponseDto.getEmployee();
        assertSame(department, actualDepartment);
        assertSame(employee, actualEmployee);
        assertSame(organization, actualApiResponseDto.getOrganization());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link APIResponseDto#APIResponseDto(EmployeeDto, DepartmentDto, OrganizationDto)}
     *   <li>{@link APIResponseDto#setDepartment(DepartmentDto)}
     *   <li>{@link APIResponseDto#setEmployee(EmployeeDto)}
     *   <li>{@link APIResponseDto#setOrganization(OrganizationDto)}
     *   <li>{@link APIResponseDto#getDepartment()}
     *   <li>{@link APIResponseDto#getEmployee()}
     *   <li>{@link APIResponseDto#getOrganization()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        EmployeeDto employee = new EmployeeDto();
        DepartmentDto department = new DepartmentDto();
        APIResponseDto actualApiResponseDto = new APIResponseDto(employee, department, new OrganizationDto());
        DepartmentDto department2 = new DepartmentDto();
        actualApiResponseDto.setDepartment(department2);
        EmployeeDto employee2 = new EmployeeDto();
        actualApiResponseDto.setEmployee(employee2);
        OrganizationDto organization = new OrganizationDto();
        actualApiResponseDto.setOrganization(organization);
        DepartmentDto actualDepartment = actualApiResponseDto.getDepartment();
        EmployeeDto actualEmployee = actualApiResponseDto.getEmployee();
        assertSame(department2, actualDepartment);
        assertSame(employee2, actualEmployee);
        assertSame(organization, actualApiResponseDto.getOrganization());
    }
}
