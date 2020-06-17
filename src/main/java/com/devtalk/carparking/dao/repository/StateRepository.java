package com.devtalk.carparking.dao.repository;

import com.devtalk.carparking.dao.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity,Long> {
}
