package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.seeddata.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "STATES")
@NoArgsConstructor
@AllArgsConstructor
public class StateEntity {

    @Id
    private Long id;

    @Column(name = "STATE_NAME")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STATE_NAME", referencedColumnName = "STATE_NAME")
    private List<CityEntity> cityEntityList;

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

