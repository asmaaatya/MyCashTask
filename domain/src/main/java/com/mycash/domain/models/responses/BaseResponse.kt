package com.mycash.domain.models.responses

open class BaseResponse(
    val message: String = "",
    val responseCode: Int = -1,
    val success: Boolean = false,
)
