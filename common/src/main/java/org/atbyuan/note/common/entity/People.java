package org.atbyuan.note.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class People implements Serializable {

    private Integer id;

    private String zipcode;

    private String address;

    private String lastname;

    private String firstname;

    private String birthdate;

    private static final long serialVersionUID = 1L;

}