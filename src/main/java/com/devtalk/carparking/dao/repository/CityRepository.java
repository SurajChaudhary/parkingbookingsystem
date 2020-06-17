package com.devtalk.carparking.dao.repository;

import com.devtalk.carparking.dao.entity.CityEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEnitity,Long> {
}
