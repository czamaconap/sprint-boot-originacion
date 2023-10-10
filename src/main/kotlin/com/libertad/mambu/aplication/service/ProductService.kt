package com.libertad.mambu.aplication.service

import com.libertad.mambu.aplication.usecases.GetProductsUseCase
import com.libertad.mambu.domain.model.Product
import org.springframework.stereotype.Service

@Service
class ProductService(private val useCase: GetProductsUseCase) {

    fun getAllProducts(): List<Product> {
        return useCase.getAllProducts()
    }
}