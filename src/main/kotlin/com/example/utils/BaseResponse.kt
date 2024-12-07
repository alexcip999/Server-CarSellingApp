package com.example.utils

import io.ktor.http.*

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode,
) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null,
        val statusCodeSucces: HttpStatusCode = HttpStatusCode.OK,
    ): BaseResponse<T>(statusCodeSucces)

    data class ErrorResponse<T>(
        val data: T? = null,
        val message: String? = null,
        val statusCodeError: HttpStatusCode = HttpStatusCode.BadRequest,
    ): BaseResponse<T>(statusCodeError)

}