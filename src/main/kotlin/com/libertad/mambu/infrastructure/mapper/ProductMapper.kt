package com.libertad.mambu.infrastructure.mapper

import com.libertad.mambu.infrastructure.adapter.RemoteProduct
import com.libertad.mambu.domain.model.Product as DomainProduct
import com.libertad.mambu.infrastructure.persistence.entity.Product as EntityProduct

class ProductMapper {

    companion object{
        fun mapToDomain(remoteProduct: RemoteProduct): DomainProduct {
            return DomainProduct(
                id = null, // You may set an appropriate ID if needed
                name = remoteProduct.name,
                price = remoteProduct.price,
                // Map other properties as needed
            )
        }

        fun mapToDomain(entity: EntityProduct): DomainProduct {
            return DomainProduct(
                id = null, // You may set an appropriate ID if needed
                name = entity.name,
                price = entity.price
            )
        }

    }


}