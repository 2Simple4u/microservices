package net.javaguides.departmentservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplDiffblueTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    /**
     * Method under test:
     * {@link DepartmentServiceImpl#saveDepartment(DepartmentDto)}
     */
    @Test
    void testSaveDepartment() {
        Department department = new Department();
        department.setDepartmentCode("Department Code");
        department.setDepartmentDescription("Department Description");
        department.setDepartmentName("Department Name");
        department.setId(1L);
        when(departmentRepository.save(Mockito.<Department>any())).thenReturn(department);
        DepartmentDto actualSaveDepartmentResult = departmentServiceImpl.saveDepartment(new DepartmentDto());
        verify(departmentRepository).save(Mockito.<Department>any());
        assertEquals("Department Code", actualSaveDepartmentResult.getDepartmentCode());
        assertEquals("Department Description", actualSaveDepartmentResult.getDepartmentDescription());
        assertEquals("Department Name", actualSaveDepartmentResult.getDepartmentName());
        assertEquals(1L, actualSaveDepartmentResult.getId().longValue());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getDepartmentByCode(String)}
     */
    @Test
    void testGetDepartmentByCode() {
        Department department = new Department();
        department.setDepartmentCode("Department Code");
        department.setDepartmentDescription("Department Description");
        department.setDepartmentName("Department Name");
        department.setId(1L);
        when(departmentRepository.findByDepartmentCode(Mockito.<String>any())).thenReturn(department);
        DepartmentDto actualDepartmentByCode = departmentServiceImpl.getDepartmentByCode("Department Code");
        verify(departmentRepository).findByDepartmentCode(Mockito.<String>any());
        assertEquals("Department Code", actualDepartmentByCode.getDepartmentCode());
        assertEquals("Department Description", actualDepartmentByCode.getDepartmentDescription());
        assertEquals("Department Name", actualDepartmentByCode.getDepartmentName());
        assertEquals(1L, actualDepartmentByCode.getId().longValue());
    }
}
