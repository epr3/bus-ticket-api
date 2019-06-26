package com.apdboo.project.repositories;

import com.apdboo.project.models.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    @Query("SELECT amenity FROM Amenity amenity WHERE amenity.id IN :amenityIds")
    List<Amenity> getAmenitiesByIds(@Param("amenityIds") List<Long> amenityIds);
}
