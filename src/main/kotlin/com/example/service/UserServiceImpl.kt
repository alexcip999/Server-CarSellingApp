package com.example.service

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.UserTable
import com.example.models.User
import com.example.security.hash
import com.example.service.dto.CreateUserParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.*
import java.util.regex.Pattern

class UserServiceImpl: UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = params.name
                it[email] = params.email
                it[password] = hash(params.password)
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }



    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email eq email }
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserByName(name: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.name eq name }
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun matchPasswords(pass1: String, pass2: String): Boolean {
        return pass1 == pass2
    }

    override suspend fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    override suspend fun changePassword(email: String, newPassword: String) {
        val hashedPassword = hash(newPassword)
        return dbQuery {
            val updatedRows = UserTable.update({ UserTable.email eq email }) {
                it[password] = hashedPassword
            }
            updatedRows > 0
        }
    }

    override suspend fun getUsers(): List<User?> =
        dbQuery {
            UserTable.selectAll().map { rowToUser(it) }
        }



    private fun rowToUser(row: ResultRow?): User? {
        return if(row == null) null
        else User(
            id = row[UserTable.id],
            name = row[UserTable.name],
            email = row[UserTable.email],
            password = row[UserTable.password],
        )
    }
}