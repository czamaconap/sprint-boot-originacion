package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Product

interface ProductRepositoryPost {

    fun getAllProducts() : List<Product>
}