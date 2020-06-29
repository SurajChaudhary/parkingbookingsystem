package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FacilityRepository extends JpaRepository<FacilityEntity, Integer> {
    FacilityEntity findByName(String testFacility123);
}
