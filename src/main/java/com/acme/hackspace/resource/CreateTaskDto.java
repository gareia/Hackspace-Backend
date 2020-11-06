package com.acme.hackspace.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateTaskDto {
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;
}
