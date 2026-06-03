package com.ie.universities.explorer.service;

import com.ie.universities.explorer.entity.*;
import com.ie.universities.explorer.repository.UniversityRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    public Iterable<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    /**
     * Get all universities by country
     * @param element, elementValue
     * @return List<University>
     */
    public List<University> getUniversitySearch(String element, String elementValue) {
        List<University> universities = new ArrayList<>();
        switch (element) {
            // Search by name
            case "Name":
                universities = universityRepository.findByName(elementValue);
                return universities;
            // Search by Id
            case "Id":
                if(universityRepository.findById(Long.valueOf(elementValue)).isPresent()) {
                    universities.add(universityRepository.findById(Long.valueOf(elementValue)).get());
                    return universities;
                }
                return null;
            // Search by country
            case "Country":
                return universityRepository.findByCountry(elementValue);
        }
        return null;
    }
}
