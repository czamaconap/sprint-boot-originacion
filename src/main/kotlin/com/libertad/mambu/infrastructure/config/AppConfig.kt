package com.libertad.mambu.infrastructure.config

import com.libertad.mambu.aplication.service.ClientService
import com.libertad.mambu.aplication.service.ContractsService
import com.libertad.mambu.aplication.service.DepositAccountService
import com.libertad.mambu.aplication.service.OnboardingService
import com.libertad.mambu.aplication.usecases.*
import com.libertad.mambu.domain.port.`in`.*
import com.libertad.mambu.domain.port.out.*
import com.libertad.mambu.domain.port.out.RemoteContractsServicePort
import com.libertad.mambu.infrastructure.adapter.RemoteClientServiceAdapter
import com.libertad.mambu.infrastructure.adapter.RemoteContractsServiceAdapter
import com.libertad.mambu.infrastructure.adapter.RemoteDepositAccountServiceAdapter
import com.libertad.mambu.infrastructure.adapter.RemoteProductServiceAdapter
import com.libertad.mambu.infrastructure.persistence.repository.JpaProductorAdapter
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Configuration
class AppConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val trustAllCertificates = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate?> = arrayOfNulls(0)

                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}

                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
            }
        )

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCertificates, java.security.SecureRandom())

        val socketFactory = SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE)

        val connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(socketFactory)
            .build()

        val httpClient: CloseableHttpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestTemplate(factory)
    }


    /*@Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }*/

    @Bean
    fun remoteProductServicePort(restTemplate: RestTemplate): RemoteProductServicePort {
        return RemoteProductServiceAdapter(restTemplate)
    }

    @Bean
    fun productRepositoryPost(jpaProductorAdapter: JpaProductorAdapter): ProductRepositoryPost {
        return jpaProductorAdapter
    }


    @Bean
    fun remoteClientServicePort(restTemplate: RestTemplate): RemoteClientServicePort {
        return RemoteClientServiceAdapter(restTemplate)
    }

    @Bean
    fun remoteDepositAccountServicePort(restTemplate: RestTemplate): RemoteDepositAccountServicePort {
        return RemoteDepositAccountServiceAdapter(restTemplate)
    }

    @Bean
    fun createClientUseCase(remoteClientServicePort: RemoteClientServicePort): CreateClientUseCase {
        return CreateClientUseCaseImpl(remoteClientServicePort)
    }

    @Bean
    fun createDepositAccountUseCase(remoteDepositAccountServicePort: RemoteDepositAccountServicePort): CreateDepositAccountUseCase {
        return CreateDepositAccountUseCaseImpl(remoteDepositAccountServicePort)
    }

    @Bean
    fun clientService(createClientUseCase: CreateClientUseCase): ClientService {
        return ClientService(createClientUseCase)
    }

    @Bean
    fun generateCBAccountUseCase(remoteDepositAccountServicePort: RemoteDepositAccountServicePort): GenerateCBAccountUseCase {
        return GenerateCBAccountUseCaseImpl(remoteDepositAccountServicePort)
    }

    @Bean
    fun remoteContractsServicePort(restTemplate: RestTemplate): RemoteContractsServicePort{
        return RemoteContractsServiceAdapter(restTemplate)
    }

    @Bean
    fun createContractUseCase(remoteContractsServicePort: RemoteContractsServicePort): CreateContractUseCase {
        return CreateContractUseCaseImpl(remoteContractsServicePort)
    }

    @Bean
    fun contractsService(createContractUseCase: CreateContractUseCase): ContractsService {
        return ContractsService(createContractUseCase)
    }

    @Bean
    fun onboardingUseCase(
        createClientUseCase: CreateClientUseCase,
        createDepositAccountUseCase: CreateDepositAccountUseCase,
        generateCBAccountUseCase: GenerateCBAccountUseCase,
        updateCBAccountUseCase: UpdateCBAccountUseCase,
        createContractUseCase: CreateContractUseCase): OnboardingUseCase {
        return OnboardingUseCaseImpl(
            createClientUseCase,
            createDepositAccountUseCase,
            generateCBAccountUseCase,
            updateCBAccountUseCase,
            createContractUseCase)
    }

    @Bean
    fun onboardingService(onboardingUseCase: OnboardingUseCase): OnboardingService {
        return OnboardingService(onboardingUseCase)
    }

  /* @Bean
    fun depositAccountService(
        createDepositAccountUseCase: CreateDepositAccountUseCase,
        generateCBAccountUseCase: GenerateCBAccountUseCase,
        updateCBAccountUseCase: UpdateCBAccountUseCase
    ): DepositAccountService {
        return DepositAccountService(
            createDepositAccountUseCase = createDepositAccountUseCase,
            generateCBAccountUseCase = generateCBAccountUseCase,
            updateCBAccountUseCase = updateCBAccountUseCase)
    }*/


    @Bean
    fun updateCBAccountUseCase(remoteDepositAccountServicePort: RemoteDepositAccountServicePort): UpdateCBAccountUseCase {
        return UpdateCBAccountUseCaseImpl(remoteDepositAccountServicePort)
    }
}
