package com.libertad.mambu.infrastructure.persistence.repository

import com.libertad.mambu.infrastructure.persistence.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface JpaProductRepository : JpaRepository<Product, Long> {


}