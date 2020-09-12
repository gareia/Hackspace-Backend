package com.acme.hackspace.resource;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResource {

    private Long id;
    
    private String name;

    private Boolean completed;

    private Date createdAt;

}
