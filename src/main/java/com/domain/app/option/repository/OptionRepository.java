package com.domain.app.option.repository;

import com.domain.app.option.domain.Option;
import com.domain.app.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByProduct(Product product);
    Optional<Option> findByProductAndName(Product product, String name);
}
