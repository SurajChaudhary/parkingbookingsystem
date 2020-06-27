package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import com.devtalk.carparking.dataaccess.repository.FacilityRepository;
import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.service.FacilityService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public List<Facility> getFacilities() {
        List<FacilityEntity> facilityEntities = facilityRepository.findAll();
        if (CollectionUtils.isEmpty(facilityEntities)) {
            throw new FacilityNotFoundException();
        }
        return facilityEntities
                .parallelStream()
                .map(FacilityEntity::getFacility)
                .collect(toList());
    }

}
