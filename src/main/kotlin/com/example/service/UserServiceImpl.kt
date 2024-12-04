package com.example.service

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.UserTable
import com.example.models.User
import com.example.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl: UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[username] = params.username
                it[password] = hash(params.password)
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByUsername(username: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.username eq username }
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if(row == null) null
        else User(
            id = row[UserTable.id],
            username = row[UserTable.username],
            password = row[UserTable.password],
        )
    }
}