package com.devtalk.carparking.dao.entity;

import com.devtalk.carparking.model.seeddata.State;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "STATES")
public class StateEntity {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public static State getStateFromStateEntity(StateEntity stateEntity){
        State state= new State();
        state.setStateName(stateEntity.getName());
        return state;
    }
    public static StateEntity getStateEntityFromStateModel(State state){
        StateEntity stateEntity= new StateEntity();
        stateEntity.setName(state.getStateName());
        return stateEntity;
    }


}

