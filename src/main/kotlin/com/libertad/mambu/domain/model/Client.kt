package com.libertad.mambu.domain.model

import com.google.gson.annotations.SerializedName

data class Client(
    @SerializedName("encodedKey")
    var encodedKey:String? = null,
    @SerializedName("firstName")
    var firstName: String? = null,
    @SerializedName("middleName")
    var middleName: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("birthDate")
    var birthDate: String? = null,
    @SerializedName("mobilePhone")
    var mobilePhone: String? = null,
    @SerializedName("emailAddress")
    var emailAddress: String? = null,
    @SerializedName("address")
    var address: List<Address>? = emptyList(),
    @SerializedName("_Clientes_N2")
    var clientN2: ClientN2? = null,
    @SerializedName("id")
    var id: String? = null,
)

data class ClientN2(
    @SerializedName("_RFC")
    var rfc: String? = null,
    @SerializedName("_CURP")
    var curp: String? = null,
)

data class Address(
    @SerializedName("encodedKey")
    var encodedKey: String? = null,
    @SerializedName("parentKey")
    var parentKey: String? = null,
    @SerializedName("line1")
    var line1: String? = null,
    @SerializedName("line2")
    var line2: String? = null,
    @SerializedName("line3")
    var line3: String? = null,
    @SerializedName("postcode")
    var postcode: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("region")
    var region: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("indexInList")
    var indexInList: Int? = null
)