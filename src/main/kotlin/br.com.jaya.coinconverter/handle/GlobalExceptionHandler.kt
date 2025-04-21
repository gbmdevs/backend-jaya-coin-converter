package br.com.jaya.coinconverter.handle

import br.com.jaya.coinconverter.exception.UserFoundedException
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse (
            code    = 500,
            message =  "An unexpected error occurred: ${ex.message}"
        )
        return ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ExpiredJwtException::class,BadCredentialsException::class,UsernameNotFoundException::class)
    fun jwtExpiredHandler(ex: ExpiredJwtException):  ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse (
            code    = 401,
            message =  "Access not authorized: ${ex.message}"
        )
        return ResponseEntity(errorResponse,HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UserFoundedException::class)
    fun businessException(ex: Exception): ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse (
            code    = 403,
            message =  "Forbidden process: ${ex.message}"
        )
        return ResponseEntity(errorResponse,HttpStatus.FORBIDDEN)
    }


}