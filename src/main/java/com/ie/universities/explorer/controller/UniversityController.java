package com.ie.universities.explorer.controller;

import com.ie.universities.explorer.entity.University;
import com.ie.universities.explorer.service.UniversityService;
import com.ie.universities.explorer.validation.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/universities")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    /**
     * Get all universities.
     * @return A list of all universities.
     */
    @GetMapping
    public ResponseEntity<Iterable<University>> getAllUniversities() {
        Iterable<University> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }

    /**
     * Search for universities by a specific element and its value.
     * @param element The field to filter by (e.g., name, id, country).
     * @param elementValue The value to filter by.
     * @return A list of universities matching the search criteria or an error message.
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchUniversities(@RequestParam(value = "element") String element,
                                                @RequestParam(value = "elementValue") String elementValue) {
        boolean isValid = ServiceValidator.validateQueryParameter(element, elementValue);
        if (!isValid) {
            return new ResponseEntity<>("Invalid filter provided.", HttpStatus.BAD_REQUEST);
        }
        try {
            List<University> universities = universityService.getUniversitySearch(element, elementValue);
            return new ResponseEntity<>(universities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing your request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}