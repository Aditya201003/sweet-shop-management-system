package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.dto.SweetResponse;
import com.incubyte.sweetshop.service.SweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {

    private final SweetService sweetService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SweetResponse> add(@RequestBody SweetRequest request) {
        return ResponseEntity.ok(sweetService.addSweet(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SweetResponse> update(
            @PathVariable Long id,
            @RequestBody SweetRequest request
    ) {
        return ResponseEntity.ok(sweetService.updateSweet(id, request));
    }


    @GetMapping
    public ResponseEntity<List<SweetResponse>> getAll() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SweetResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max
    ) {
        return ResponseEntity.ok(
                sweetService.search(name, category, min, max)
        );
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<SweetResponse> purchase(@PathVariable Long id) {
        return ResponseEntity.ok(sweetService.purchase(id));
    }

    @PostMapping("/{id}/restock")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SweetResponse> restock(
            @PathVariable Long id,
            @RequestParam int qty
    ) {
        return ResponseEntity.ok(sweetService.restock(id, qty));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sweetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
