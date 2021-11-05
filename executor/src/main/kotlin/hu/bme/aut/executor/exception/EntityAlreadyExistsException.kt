package hu.bme.aut.executor.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class EntityAlreadyExistsException(message: String) : RuntimeException(message)