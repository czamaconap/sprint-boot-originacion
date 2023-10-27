package com.libertad.mambu.infrastructure.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.commons.io.IOUtils
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException
import org.springframework.http.*
import java.nio.charset.StandardCharsets

class MyResponseErrorHandler : ResponseErrorHandler {

     @Throws(IOException::class)
     override fun hasError(response: ClientHttpResponse): Boolean {
         TODO("Not yet implemented")
         return response.statusCode.is4xxClientError || response.statusCode.is5xxServerError
     }
     @Throws(IOException::class)
     override fun handleError(response: ClientHttpResponse) {
         if (response.statusCode.is5xxServerError) {
             if (response.statusCode == HttpStatus.SERVICE_UNAVAILABLE) {
                 //ServiceUnAvailableException("Service is currently unavailable");
             }
             // handle more server errors
         } else if (response.statusCode.is4xxClientError) {

         }
     }
 }