package com.libertad.mambu.domain.port.out

import com.libertad.mambu.domain.model.Product

interface RemoteProductServicePort {

    fun getAllProducts() : List<Product>
}