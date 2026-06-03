package com.ie.universities.explorer.repository;

import com.ie.universities.explorer.entity.University;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {

    // Custom query to find by country
    @Query(value = "SELECT * FROM university WHERE country = ?1" , nativeQuery = true)
    List<University> findByCountry(String country);
    // Custom query to find by name
    @Query(value = "SELECT * FROM university WHERE name LIKE %?1" , nativeQuery = true)
    List<University> findByName(String name);
}
