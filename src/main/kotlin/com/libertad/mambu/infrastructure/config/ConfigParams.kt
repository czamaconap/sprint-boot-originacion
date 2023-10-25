package com.libertad.mambu.infrastructure.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration //@PropertySource("classpath:application.properties")
//DEV Local
//@PropertySource("file:/C:\\Users\\NS-714\\Documents\\workspace\\odc_params.properties")
@PropertySource("file:/C:\\Users\\user.crea40\\Documents\\Libertad\\team_mambu\\src\\sprint-boot-originacion\\app_params.properties") //@PropertySource("file:odc_params.properties")
//@PropertySource("file:/C:\\Users\\user.crea41\\Documents\\proyectos\\sprint-boot-originacion\\app_params.properties") //@PropertySource("file:odc_params.properties")

//@PropertySource("file:app_params.properties") //Docker
class ConfigParams(
    @Value("\${api.url}") private val apiURL: String,
    @Value("\${api.url.contratos}") private val apiUrlContratos: String,
    @Value("\${api.client_id}") private val apiClientId: String,
    @Value("\${api.client_secret}") private val apiClientSecret: String,
) {
    val API_URL: String get() = apiURL
    val API_CLIENT_ID: String get() = apiClientId
    val API_CLIENT_SECRET: String get() = apiClientSecret

    val API_URL_CONTRATOS: String get() = apiUrlContratos

    private fun decrypt(encryptedValue: String): String {
        var result: String = ""
        if (encryptedValue.trim() != "") {
            result =  encryptedValue;
        }
        return  result
    }
}