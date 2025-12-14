package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, Long> {

    @Query("""
        SELECT s FROM Sweet s
        WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:category IS NULL OR LOWER(s.category) = LOWER(:category))
          AND (:minPrice IS NULL OR s.price >= :minPrice)
          AND (:maxPrice IS NULL OR s.price <= :maxPrice)
    """)
    List<Sweet> search(
            @Param("name") String name,
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
