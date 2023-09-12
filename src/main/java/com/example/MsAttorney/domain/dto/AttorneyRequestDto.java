package com.example.MsAttorney.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AttorneyRequestDto implements Serializable {

    private static final long serialVersionUID = 8222253670338491507L;
    private String name;
    private String fatherlastname;
    private String motherlastname;
    private String dni;
    private String cellphone;
    private String address;
    private String active;
}
