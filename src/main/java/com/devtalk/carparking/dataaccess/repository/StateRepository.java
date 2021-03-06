package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity, Long> {
}
