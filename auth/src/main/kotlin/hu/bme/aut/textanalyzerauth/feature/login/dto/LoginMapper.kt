package hu.bme.aut.textanalyzerauth.feature.login.dto

import hu.bme.aut.textanalyzerauth.domain.User

fun User.toUserResponse() = UserResponse(id = id!!, username = username, email = email, role = role)