package com.example.oauth.infrastructure.feign.config

import com.example.oauth.infrastructure.feign.error.FeignClientErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.example.oauth"])
@Configuration
class FeignConfig {
    @Bean
    @ConditionalOnMissingBean(value = [FeignClientErrorDecoder::class])
    fun commonFeignErrorDecorder(): FeignClientErrorDecoder {
        return FeignClientErrorDecoder()
    }
}