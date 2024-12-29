package com.example.service

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.UserDetailsTable
import com.example.db.UserDetailsTable.city
import com.example.db.UserDetailsTable.country
import com.example.db.UserDetailsTable.name
import com.example.db.UserDetailsTable.phone
import com.example.db.UserDetailsTable.profileImageUri
import com.example.db.UserDetailsTable.userId
import com.example.db.UserTable
import com.example.db.UserTable.id
import com.example.models.User
import com.example.models.UserDetails
import com.example.service.dto.UserDetailsParam
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update

class UserDetailsServiceImpl : UserDetailsService {
    override suspend fun getUserDetailsById(id: Int): List<UserDetails?> {
        return dbQuery {
            UserDetailsTable.select{ UserDetailsTable.userId eq id}.map { rowToUserDetails(it) }
        }
    }


    override suspend fun saveUserDetails(userDetailsParam: UserDetailsParam): UserDetails? {
        return dbQuery {
            // Check if the user details already exist
            val existingUserDetails = UserDetailsTable.select { UserDetailsTable.userId eq userDetailsParam.userId }
                .singleOrNull()

            if (existingUserDetails != null) {
                // If the user details already exist, update them
                val updatedRowCount = UserDetailsTable.update({ UserDetailsTable.userId eq userDetailsParam.userId }) {
                    it[name] = userDetailsParam.name
                    it[country] = userDetailsParam.country
                    it[city] = userDetailsParam.city
                    it[phone] = userDetailsParam.phone
                    it[profileImageUri] = userDetailsParam.profileImageUri
                }

                if (updatedRowCount > 0) {
                    return@dbQuery rowToUserDetails(existingUserDetails)
                } else {
                    return@dbQuery null
                }
            } else {
                // If no existing record, insert new user details
                val statement = UserDetailsTable.insert {
                    it[userId] = userDetailsParam.userId
                    it[name] = userDetailsParam.name
                    it[country] = userDetailsParam.country
                    it[city] = userDetailsParam.city
                    it[phone] = userDetailsParam.phone
                    it[profileImageUri] = userDetailsParam.profileImageUri
                }

                return@dbQuery rowToUserDetails(statement.resultedValues?.get(0))
            }
        }
    }

    private fun rowToUserDetails(row: ResultRow?): UserDetails? {
        return if(row == null) null
        else UserDetails(
            userId = row[userId],
            name = row[name],
            country = row[country],
            city = row[city],
            phone = row[phone],
            profileImageUri = row[profileImageUri]
        )
    }
}