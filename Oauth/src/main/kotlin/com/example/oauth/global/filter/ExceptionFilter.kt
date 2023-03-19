package com.example.oauth.global.filter

import com.example.oauth.global.error.ErrorResponse
import com.example.oauth.global.error.exception.OAuthException
import com.example.oauth.global.exception.InternalServerError
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            exception.printStackTrace()
            when (exception) {
                is OAuthException -> writeErrorCode(exception, response)
                else -> writeErrorCode(InternalServerError, response)
            }
        }
    }

    private fun writeErrorCode(exception: OAuthException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse.of(exception)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = errorResponse.status
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}