package com.example.repository

import com.example.models.UserDetails
import com.example.service.dto.UserDetailsParam
import com.example.utils.BaseResponse

interface UserDetailsRepository {
    suspend fun getUserDetailsById(id: Int): List<UserDetails?>
    suspend fun saveUserDetails(userDetailsParam: UserDetailsParam): BaseResponse<Any>
}