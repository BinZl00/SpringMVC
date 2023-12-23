package com.bz.PoJo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data

public class tUser implements Serializable {
    private Long id;
    private String name;
    private Integer age;

}