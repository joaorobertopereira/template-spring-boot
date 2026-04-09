package com.template.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Health check endpoints")
class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns API status and current thread info")
    @ApiResponse(responseCode = "200", description = "API is healthy")
    fun health(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(
            mapOf(
                "status" to "UP",
                "thread" to Thread.currentThread().toString()
            )
        )
    }
}
