package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.request.StateRequest;
import com.devtalk.carparking.model.seeddata.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "STATES")
@NoArgsConstructor
@AllArgsConstructor
public class StateEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "STATE_ID")
    private long stateId;
    @Column(name = "STATE_NAME")
    private String stateName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STATE_ID", referencedColumnName = "STATE_ID")
    private List<CityEntity> cities;

    public static State getStateFromStateEntity(StateEntity entity) {
        State state = new State();
        state.setId(entity.getId());
        state.setStateId(entity.getStateId());
        state.setStateName(entity.getStateName());
        state.setCities(entity.getCities());
        return state;
    }

    public static StateEntity getEntityFromStateRequest(StateRequest state) {
        StateEntity stateEntity = new StateEntity();
        stateEntity.setStateId(state.getStateId());
        stateEntity.setStateName(state.getStateName());
        return stateEntity;
    }


}

