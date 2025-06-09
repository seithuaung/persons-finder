package com.persons.finder.exceptionhandling

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleNotFoundException(e: Exception): ResponseEntity<Message> {
        return ResponseEntity.ok(Message(HttpStatus.INTERNAL_SERVER_ERROR.name, HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase ))
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFoundException(e: NoSuchElementException): ResponseEntity<Message> {
        return ResponseEntity.ok(Message(HttpStatus.NOT_FOUND.name, HttpStatus.NOT_FOUND.reasonPhrase ))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<Message> {
        return ResponseEntity.ok(Message(HttpStatus.BAD_REQUEST.name, HttpStatus.BAD_REQUEST.reasonPhrase) )
    }
}