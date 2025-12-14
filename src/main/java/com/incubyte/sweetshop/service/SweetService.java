package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.dto.SweetResponse;

import java.util.List;

public interface SweetService {

    SweetResponse addSweet(SweetRequest request);

    List<SweetResponse> getAllSweets();

    List<SweetResponse> search(String name, String category, Double minPrice, Double maxPrice);

    SweetResponse purchase(Long id);

    SweetResponse restock(Long id, int quantity);

    SweetResponse updateSweet(Long id, SweetRequest request);

    void delete(Long id);
}
