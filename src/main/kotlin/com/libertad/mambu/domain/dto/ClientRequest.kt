package com.libertad.mambu.domain.dto

data class ClientRequest(
    var firstname: String? = null,
    var middlename: String? = null,
    var lastname: String? = null,
    var rs_fechanacimiento: String? = null,
    var mobilephone: String? = null,
    var rs_rfc : String? = null,
    var rs_curp: String? = null,
    var emailaddress1: String? = null,
    var address1_line1: String? = null,
    var address1_line2: String? = null,
    var address1_line3: String? = null,
    var address1_postalcode: String? = null,
    var rs_municipio: String? = null,
    var rs_numerocuenta: String? = null,
    var rs_numerocliente: String? = null,
    var rs_clave: String? = null,
)