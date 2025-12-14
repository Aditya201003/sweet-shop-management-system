package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SweetRepository extends JpaRepository<Sweet, Long> {

    @Query(value = """
        SELECT * FROM sweet s
        WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', CAST(:name AS TEXT), '%')))
          AND (:category IS NULL OR LOWER(s.category) = LOWER(CAST(:category AS TEXT)))
          AND (:min IS NULL OR s.price >= :min)
          AND (:max IS NULL OR s.price <= :max)
        """, nativeQuery = true)
    List<Sweet> search(
            @Param("name") String name,
            @Param("category") String category,
            @Param("min") Double min,
            @Param("max") Double max
    );
}
