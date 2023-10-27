package com.libertad.mambu.domain.model


class MyCustomException(message: String?, val service: String, val errList: ErrorResponse) : RuntimeException(message)
