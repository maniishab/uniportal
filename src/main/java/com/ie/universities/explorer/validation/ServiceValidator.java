package com.ie.universities.explorer.validation;

import org.springframework.util.StringUtils;

public class ServiceValidator {

    /**
     * Validate the query parameter
     * @param element
     * @param elementValue
     * @return boolean
     */
    public static boolean validateQueryParameter(String element, String elementValue){

        if(StringUtils.hasText(element) & StringUtils.hasText(elementValue)){
            if(element.equals("Id") | element.equals("Country") | element.equals("Name")) {
                if(element.equals("Id") & !elementValue.chars().allMatch( Character::isDigit)){
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
