package com.libertad.mambu.domain.model;

data class MambuError(val errors: List<Error>)

data class Error(val errorCode: Int, val errorReason: String)
