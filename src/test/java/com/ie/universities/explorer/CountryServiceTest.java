package com.ie.universities.explorer;

import com.ie.universities.explorer.dto.CountryDTO;
import com.ie.universities.explorer.entity.Country;
import com.ie.universities.explorer.mapper.CountryMapper;
import com.ie.universities.explorer.repository.CountryRepository;
import com.ie.universities.explorer.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for getAllCountries method
    @Test
    @DisplayName("Test getAllCountries")
    void testGetAllCountries() {
        Country country = new Country();
        country.setId(1);
        country.setIso("US");
        country.setName("United States");
        country.setNicename("United States");
        country.setIso3("USA");
        country.setNumcode((short) 840);
        country.setPhonecode(1);

        List<Country> countries = Arrays.asList(country);
        when(countryRepository.findAll()).thenReturn(countries);

        CountryDTO countryDTO = new CountryDTO("United States", "US");
        List<CountryDTO> countryDTOs = Arrays.asList(countryDTO);
        when(countryMapper.getCountries(countries)).thenReturn(countryDTOs);

        List<CountryDTO> result = countryService.getAllCountries();
        assertEquals(1, result.size());
        assertEquals("United States", result.get(0).getCountry());
        assertEquals("US", result.get(0).getIso());

        verify(countryRepository, times(1)).findAll();
        verify(countryMapper, times(1)).getCountries(countries);
    }
}
