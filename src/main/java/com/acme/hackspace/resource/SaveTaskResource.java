package com.acme.hackspace.resource;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveTaskResource {
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    private Boolean completed;

    private Date createdAt;
}
