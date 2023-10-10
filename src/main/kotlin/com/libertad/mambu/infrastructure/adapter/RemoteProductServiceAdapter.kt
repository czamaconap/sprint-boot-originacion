package com.libertad.mambu.infrastructure.adapter

import com.libertad.mambu.domain.model.Product
import com.libertad.mambu.domain.port.out.RemoteProductServicePort
import com.libertad.mambu.infrastructure.mapper.ProductMapper
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class RemoteProductServiceAdapter(
    private val restTemplate: RestTemplate
) : RemoteProductServicePort {

    override fun getAllProducts(): List<Product> {
        val response = restTemplate.exchange(
            "https://api.example.com/products",
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<RemoteProduct>>() {}
        )
        var list = response.body ?: emptyList()
        return list.map { ProductMapper.mapToDomain(it) }
    }
}

data class RemoteProduct(var id: Long, var name: String, var price: Double)