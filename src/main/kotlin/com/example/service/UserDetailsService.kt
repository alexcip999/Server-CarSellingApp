package com.example.service

import com.example.models.UserDetails
import com.example.service.dto.UserDetailsParam
import com.example.utils.BaseResponse

interface UserDetailsService {
    suspend fun getUserDetailsById(id: Int): List<UserDetails?>
    suspend fun saveUserDetails(userDetailsParam: UserDetailsParam): UserDetails?
}