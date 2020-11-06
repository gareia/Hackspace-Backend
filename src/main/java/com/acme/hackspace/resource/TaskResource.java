package com.acme.hackspace.resource;


import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskResource {

    private Long id;
    private String name;
    private Boolean completed;
    private Date createdAt;
}
