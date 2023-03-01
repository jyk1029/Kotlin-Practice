package com.example.oauth.infrastructure.feign.error

import com.example.oauth.infrastructure.feign.exception.FeignBadRequestException
import com.example.oauth.infrastructure.feign.exception.FeignExpiredTokenException
import com.example.oauth.infrastructure.feign.exception.FeignForbiddenException
import com.example.oauth.infrastructure.feign.exception.FeignUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if(response.status() >= 400) {
            when(response.status()) {
                401 -> throw FeignUnAuthorizedException.EXCEPTION
                403 -> throw FeignForbiddenException.EXCEPTION
                419 -> throw FeignExpiredTokenException.EXCEPTION
                else -> throw FeignBadRequestException.EXCEPTION
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}