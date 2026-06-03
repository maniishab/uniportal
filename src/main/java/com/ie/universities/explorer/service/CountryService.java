package com.ie.universities.explorer.service;

import com.ie.universities.explorer.dto.CountryDTO;
import com.ie.universities.explorer.entity.Country;
import com.ie.universities.explorer.mapper.CountryMapper;
import com.ie.universities.explorer.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository repository;

    @Autowired
    CountryMapper mapper;

    public List<CountryDTO> getAllCountries() {

        List<Country> listAllCountries = repository.findAll();
        List<CountryDTO> listDTO = mapper.getCountries(listAllCountries);

        return listDTO;
    }
}
