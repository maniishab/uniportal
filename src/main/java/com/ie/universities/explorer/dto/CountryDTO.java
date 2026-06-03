package com.ie.universities.explorer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for countries.
 */
@Data
@AllArgsConstructor
public class CountryDTO {
    private String country;
    private String iso;
}
