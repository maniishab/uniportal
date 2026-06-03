package com.ie.universities.explorer.mapper;

import com.ie.universities.explorer.dto.CountryDTO;
import com.ie.universities.explorer.entity.Country;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Get All countries mapping to DTO entity to avoid irrelevant data
 */
@Component
public class CountryMapper {

    public List<CountryDTO> getCountries (List<Country> listAllCountries){

        List<CountryDTO> countryDTOList = listAllCountries.stream()
                .map(country -> new CountryDTO(country.getNicename(), country.getIso()))
                .collect(Collectors.toList());

        return countryDTOList;
    }
}
