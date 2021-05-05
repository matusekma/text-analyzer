package hu.bme.aut.textanalyzerauth.feature.registration.dto

import hu.bme.aut.textanalyzerauth.domain.User

fun User.toRegistrationResponse() = RegistrationResponse(id = id!!, email = email, username = username)