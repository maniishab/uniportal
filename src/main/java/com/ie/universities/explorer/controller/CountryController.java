package com.ie.universities.explorer.controller;

import com.ie.universities.explorer.dto.CountryDTO;
import com.ie.universities.explorer.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    /*
    This method is used to get all the countries
     */
    @GetMapping
    public Iterable<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }
}
