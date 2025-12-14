package com.incubyte.sweetshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SweetResponse {

    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
}
