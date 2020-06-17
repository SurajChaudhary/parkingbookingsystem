package com.devtalk.carparking.dao.repository;

import com.devtalk.carparking.dao.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<CityEntity,Integer> {
}
