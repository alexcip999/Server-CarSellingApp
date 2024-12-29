package com.example.repository

import com.example.db.DatabaseFactory.dbQuery
import com.example.models.UserDetails
import com.example.service.UserDetailsService
import com.example.service.dto.UserDetailsParam
import com.example.utils.BaseResponse

class UserDetailsRepositoryImpl(
    private val userDetailsService: UserDetailsService
) : UserDetailsRepository {
    override suspend fun getUserDetailsById(id: Int): List<UserDetails?> {
        val userDetails = userDetailsService.getUserDetailsById(id)
        return userDetails
    }

    override suspend fun saveUserDetails(userDetailsParam: UserDetailsParam): BaseResponse<Any> {
        val userDetails = userDetailsService.saveUserDetails(userDetailsParam)
        return if (userDetails == null){
            BaseResponse.ErrorResponse("Error to save user details", "Error")
        }else{
            BaseResponse.SuccessResponse("Success to save user details", "Success")
        }
    }
}