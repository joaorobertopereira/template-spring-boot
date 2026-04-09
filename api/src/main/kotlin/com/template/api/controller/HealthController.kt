package com.template.api.controller

import com.template.api.generated.controller.HealthApi
import com.template.api.generated.model.HealthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController : HealthApi {

    override fun healthCheck(): ResponseEntity<HealthResponse> {
        return ResponseEntity.ok(
            HealthResponse(
                status = "UP",
                thread = Thread.currentThread().toString()
            )
        )
    }
}
