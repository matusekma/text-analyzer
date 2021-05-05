package hu.bme.aut.textanalyzerauth.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyExistsException(message: String) :
    RuntimeException("There is already an account with that username/email: $message")
