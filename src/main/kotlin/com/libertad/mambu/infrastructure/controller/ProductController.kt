package com.libertad.mambu.infrastructure.controller

import com.libertad.mambu.domain.model.Product
import com.libertad.mambu.aplication.usecases.GetProductsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val getProductsUseCase: GetProductsUseCase) {

    @GetMapping
    fun getAllProducts(): List<Product> {
        return getProductsUseCase.getAllProducts()
    }
}