package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Integer> {
}
