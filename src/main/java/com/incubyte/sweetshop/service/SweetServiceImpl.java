package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.dto.SweetResponse;
import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetServiceImpl implements SweetService {

    private final SweetRepository sweetRepository;

    @Override
    public SweetResponse addSweet(SweetRequest request) {
        Sweet sweet = new Sweet(
                null,
                request.getName(),
                request.getCategory(),
                request.getPrice(),
                request.getQuantity()
        );

        return toResponse(sweetRepository.save(sweet));
    }

    @Override
    public List<SweetResponse> getAllSweets() {
        return sweetRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public SweetResponse updateSweet(Long id, SweetRequest request) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        sweet.setName(request.getName());
        sweet.setCategory(request.getCategory());
        sweet.setPrice(request.getPrice());
        sweet.setQuantity(request.getQuantity());

        return toResponse(sweetRepository.save(sweet));
    }


    @Override
    public List<SweetResponse> search(String name, String category, Double min, Double max) {

        String safeName = (name == null || name.isBlank())
                ? null
                : name.toLowerCase();

        String safeCategory = (category == null || category.isBlank())
                ? null
                : category.toLowerCase();

        return sweetRepository.search(safeName, safeCategory, min, max)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    @Override
    public SweetResponse purchase(Long id) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow();

        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Out of stock");
        }

        sweet.setQuantity(sweet.getQuantity() - 1);
        return toResponse(sweetRepository.save(sweet));
    }

    @Override
    public SweetResponse restock(Long id, int quantity) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow();

        sweet.setQuantity(sweet.getQuantity() + quantity);
        return toResponse(sweetRepository.save(sweet));
    }

    @Override
    public void delete(Long id) {
        sweetRepository.deleteById(id);
    }

    private SweetResponse toResponse(Sweet sweet) {
        return new SweetResponse(
                sweet.getId(),
                sweet.getName(),
                sweet.getCategory(),
                sweet.getPrice(),
                sweet.getQuantity()
        );
    }
}
