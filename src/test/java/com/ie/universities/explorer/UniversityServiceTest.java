package com.ie.universities.explorer;

import com.ie.universities.explorer.entity.University;
import com.ie.universities.explorer.repository.UniversityRepository;
import com.ie.universities.explorer.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UniversityServiceTest {

    @Mock
    private UniversityRepository universityRepository;

    @InjectMocks
    private UniversityService universityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
	@DisplayName("Test getAllUniversities")
    void testGetAllUniversities() {
        University university = new University();
        university.setId(1L);
        university.setName("Harvard University");
        university.setCountry("US");
        university.setStateProvince("MA");
        university.setAlphaTwoCode("US");
        university.setDomains(new String[]{"harvard.edu"});
        university.setWebPages(new String[]{"http://www.harvard.edu"});

        List<University> universities = Arrays.asList(university);
        when(universityRepository.findAll()).thenReturn(universities);

        Iterable<University> result = universityService.getAllUniversities();
        assertEquals(1, ((List<University>) result).size());
        assertEquals("Harvard University", ((List<University>) result).get(0).getName());

        verify(universityRepository, times(1)).findAll();
    }

    @Test
	@DisplayName("Test getUniversitySearchByName")
    void testGetUniversitySearchByName() {
        University university = new University();
        university.setId(1L);
        university.setName("Harvard University");
        university.setCountry("US");
        university.setStateProvince("MA");
        university.setAlphaTwoCode("US");
        university.setDomains(new String[]{"harvard.edu"});
        university.setWebPages(new String[]{"http://www.harvard.edu"});

        List<University> universities = Arrays.asList(university);
        when(universityRepository.findByName("Harvard")).thenReturn(universities);

        List<University> result = universityService.getUniversitySearch("Name", "Harvard");
        assertEquals(1, result.size());
        assertEquals("Harvard University", result.get(0).getName());

        verify(universityRepository, times(1)).findByName("Harvard");
    }

    @Test
	@DisplayName("Test getUniversitySearchByCountry")
    void testGetUniversitySearchByCountry() {
        University university = new University();
        university.setId(1L);
        university.setName("Harvard University");
        university.setCountry("US");
        university.setStateProvince("MA");
        university.setAlphaTwoCode("US");
        university.setDomains(new String[]{"harvard.edu"});
        university.setWebPages(new String[]{"http://www.harvard.edu"});

        List<University> universities = Arrays.asList(university);
        when(universityRepository.findByCountry("US")).thenReturn(universities);

        List<University> result = universityService.getUniversitySearch("Country", "US");
        assertEquals(1, result.size());
        assertEquals("Harvard University", result.get(0).getName());

        verify(universityRepository, times(1)).findByCountry("US");
    }

    @Test
	@DisplayName("Test getUniversitySearchInvalidElement")
    void testGetUniversitySearchInvalidElement() {
        List<University> result = universityService.getUniversitySearch("Invalid", "Value");
        assertNull(result);
    }
}