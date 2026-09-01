package com.example.workmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findByPlace(String place);
}