package com.template.crosscutting.exception

open class BusinessException(message: String) : RuntimeException(message)

class NotFoundException(message: String) : BusinessException(message)
