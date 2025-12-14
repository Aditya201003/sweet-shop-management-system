package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.dto.SweetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class SweetServiceTest {

    @Autowired
    private SweetService sweetService;

    @Test
    void shouldDecreaseQuantityWhenSweetIsPurchased() {

        SweetRequest request = new SweetRequest();
        request.setName("Gulab Jamun");
        request.setCategory("Milk Sweet");
        request.setPrice(20.0);
        request.setQuantity(5);

        SweetResponse created = sweetService.addSweet(request);

        SweetResponse purchased = sweetService.purchase(created.getId());

        assertEquals(4, purchased.getQuantity());
    }

    @Test
    void shouldIncreaseQuantityWhenSweetIsRestocked() {

        SweetRequest request = new SweetRequest();
        request.setName("Rasgulla");
        request.setCategory("Bengali Sweet");
        request.setPrice(25.0);
        request.setQuantity(10);

        SweetResponse created = sweetService.addSweet(request);

        SweetResponse restocked = sweetService.restock(created.getId(), 5);

        assertEquals(15, restocked.getQuantity());
    }
}
