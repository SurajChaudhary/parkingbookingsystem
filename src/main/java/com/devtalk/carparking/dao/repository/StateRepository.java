package com.devtalk.carparking.dao.repository;

import com.devtalk.carparking.dao.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity,Long> {
    List<StateEntity> findAllByNameIn(List<String> stateNames);
}
