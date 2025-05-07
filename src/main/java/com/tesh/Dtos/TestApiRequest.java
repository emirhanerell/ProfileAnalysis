package com.tesh.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestApiRequest {
    private String name;
    private double price;
    private int age;
    private String address;
    private String email;
}
