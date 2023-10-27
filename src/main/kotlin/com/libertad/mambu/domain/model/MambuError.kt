package com.libertad.mambu.domain.model;

data class Error(val errorCode: Int, val errorSource: String, val errorReason: String)

data class ErrorResponse(val errors: List<Error>)