package com.libertad.mambu.domain.mapper

import com.libertad.mambu.domain.dto.ClientRequest
import com.libertad.mambu.domain.model.Address
import com.libertad.mambu.domain.model.ClientN2
import com.libertad.mambu.domain.model.Client as DomainClient

class ClientMapper {

    companion object {

        fun mapToDomain(dto: ClientRequest): DomainClient {
            return DomainClient(
                firstName = dto.firstname,
                middleName = dto.middlename,
                lastName = dto.lastname,
                birthDate = dto.rs_fechanacimiento,
                mobilePhone = dto.mobilephone,
                emailAddress = dto.emailaddress1,
                addresses = arrayListOf(
                    Address(
                        line1 = dto.address1_line1,
                        line2 = dto.address1_line2,
                        line3 = dto.address1_line3,
                        postcode = dto.address1_postalcode,
                        city = dto.rs_municipio
                    )
                ),
                clientN2 = ClientN2(
                    curp = dto.rs_curp,
                    rfc = dto.rs_rfc
                ),
                id = dto.rs_numerocliente
            )
        }
    }
}