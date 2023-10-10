package com.libertad.mambu.infrastructure.config

import com.libertad.mambu.domain.port.out.ProductRepositoryPost
import com.libertad.mambu.domain.port.out.RemoteProductServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteProductServiceAdapter
import com.libertad.mambu.infrastructure.persistence.repository.JpaProductorAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class AppConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun remoteProductServicePort(restTemplate: RestTemplate): RemoteProductServicePort {
        return RemoteProductServiceAdapter(restTemplate)
    }

    @Bean
    fun productRepositoryPost(jpaProductorAdapter: JpaProductorAdapter): ProductRepositoryPost {
        return jpaProductorAdapter
    }
}