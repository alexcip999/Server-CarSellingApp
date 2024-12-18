package com.example.utils

import io.ktor.http.*

sealed class BaseResponse<T>(

) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null,
    ): BaseResponse<T>()

    data class ErrorResponse<T>(
        val data: T? = null,
        val message: String? = null,
    ): BaseResponse<T>()
}