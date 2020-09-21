package com.acme.hackspace.resource;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskResource {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

}
