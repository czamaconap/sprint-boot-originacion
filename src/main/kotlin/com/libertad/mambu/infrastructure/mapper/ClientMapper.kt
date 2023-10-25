package com.libertad.mambu.infrastructure.mapper

import com.libertad.mambu.domain.model.Address
import com.libertad.mambu.domain.model.ClientN2
import com.libertad.mambu.infrastructure.adapter.RemoteAddress
import com.libertad.mambu.infrastructure.adapter.RemoteClient
import com.libertad.mambu.infrastructure.adapter.RemoteClientN2
import com.libertad.mambu.domain.model.Client as DomainClient

class ClientMapper {

    companion object {

        fun mapToRemote(domain: DomainClient): RemoteClient {
            return RemoteClient(
                id = domain.id,
                firstName = domain.firstName,
                middleName = domain.middleName,
                lastName = domain.lastName,
                birthDate = domain.birthDate,
                mobilePhone = domain.mobilePhone,
                emailAddress = domain.emailAddress,
                clientN2 = domain.clientN2?.let {
                    RemoteClientN2(
                        curp = it.curp,
                        rfc = it.rfc
                    )
                },
                addresses = domain.addresses?.let {
                    arrayListOf(
                        RemoteAddress(
                            line1 = it[0].line1,
                            line2 = it[0].line2,
                            line3 = it[0].line3,
                            postcode = it[0].postcode,
                            city = it[0].city
                        )
                    )
                },
            )
        }

        fun mapToDomain(remote: RemoteClient): DomainClient {
            return DomainClient(
                id = remote.id,
                firstName = remote.firstName,
                middleName = remote.middleName,
                lastName = remote.lastName,
                birthDate = remote.birthDate,
                mobilePhone = remote.mobilePhone,
                emailAddress = remote.emailAddress,
                clientN2 = remote.clientN2?.let {
                    ClientN2(
                        curp = it.curp,
                        rfc = it.rfc
                    )
                },
                addresses = remote.addresses?.let {
                    arrayListOf(
                        Address(
                            line1 = it[0].line1,
                            line2 = it[0].line2,
                            line3 = it[0].line3,
                            postcode = it[0].postcode,
                            city = it[0].city
                        )
                    )
                },
            )
        }
    }
}