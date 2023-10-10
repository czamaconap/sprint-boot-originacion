package com.libertad.mambu.infrastructure.persistence.repository

import com.libertad.mambu.domain.model.Product
import com.libertad.mambu.domain.port.out.ProductRepositoryPost
import com.libertad.mambu.infrastructure.mapper.ProductMapper
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class JpaProductorAdapter(
    private var productRepository: JpaProductRepository
): ProductRepositoryPost {

    override fun getAllProducts(): List<Product> {

        return productRepository.findAll().stream()
            .map(ProductMapper::mapToDomain)
            .collect(Collectors.toList());
    }

}