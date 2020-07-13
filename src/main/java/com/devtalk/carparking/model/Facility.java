package com.devtalk.carparking.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about parking facility")
public class Facility {
    @ApiModelProperty(notes = "System Id of the parking facility")
    private long id;
    @ApiModelProperty(notes = "Name of the parking facility")
    private String name;
    @ApiModelProperty(notes = "Address of the parking facility")
    private String address;
}
