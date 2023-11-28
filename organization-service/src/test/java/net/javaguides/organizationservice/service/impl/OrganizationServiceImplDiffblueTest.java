package net.javaguides.organizationservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrganizationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrganizationServiceImplDiffblueTest {
    @MockBean
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    /**
     * Method under test:
     * {@link OrganizationServiceImpl#saveOrganization(OrganizationDto)}
     */
    @Test
    void testSaveOrganization() {
        Organization organization = new Organization();
        organization.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        organization.setId(1L);
        organization.setOrganizationCode("Organization Code");
        organization.setOrganizationDescription("Organization Description");
        organization.setOrganizationName("Organization Name");
        when(organizationRepository.save(Mockito.<Organization>any())).thenReturn(organization);
        OrganizationDto actualSaveOrganizationResult = organizationServiceImpl.saveOrganization(new OrganizationDto());
        verify(organizationRepository).save(Mockito.<Organization>any());
        assertEquals("00:00", actualSaveOrganizationResult.getCreatedDate().toLocalTime().toString());
        assertEquals("Organization Code", actualSaveOrganizationResult.getOrganizationCode());
        assertEquals("Organization Description", actualSaveOrganizationResult.getOrganizationDescription());
        assertEquals("Organization Name", actualSaveOrganizationResult.getOrganizationName());
        assertEquals(1L, actualSaveOrganizationResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link OrganizationServiceImpl#getOrganizationByCode(String)}
     */
    @Test
    void testGetOrganizationByCode() {
        Organization organization = new Organization();
        organization.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        organization.setId(1L);
        organization.setOrganizationCode("Organization Code");
        organization.setOrganizationDescription("Organization Description");
        organization.setOrganizationName("Organization Name");
        when(organizationRepository.findByOrganizationCode(Mockito.<String>any())).thenReturn(organization);
        OrganizationDto actualOrganizationByCode = organizationServiceImpl.getOrganizationByCode("Organization Code");
        verify(organizationRepository).findByOrganizationCode(Mockito.<String>any());
        assertEquals("00:00", actualOrganizationByCode.getCreatedDate().toLocalTime().toString());
        assertEquals("Organization Code", actualOrganizationByCode.getOrganizationCode());
        assertEquals("Organization Description", actualOrganizationByCode.getOrganizationDescription());
        assertEquals("Organization Name", actualOrganizationByCode.getOrganizationName());
        assertEquals(1L, actualOrganizationByCode.getId().longValue());
    }
}
