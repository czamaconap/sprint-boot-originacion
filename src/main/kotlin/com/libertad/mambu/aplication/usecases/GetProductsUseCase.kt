package com.libertad.mambu.aplication.usecases

import com.libertad.mambu.domain.model.Product
import org.springframework.stereotype.Service

@Service
class GetProductsUseCase() {

    fun getAllProducts(): List<Product> {

        // Fetch products from the local database
        //val localProducts = productService.getAllProducts()

        // Fetch products from the remote service
        //val remoteProducts = remoteProductService.getAllRemoteProducts()


        // Combine and return the products
        //return localProducts + mappedRemoteProducts
        return emptyList()
    }
}