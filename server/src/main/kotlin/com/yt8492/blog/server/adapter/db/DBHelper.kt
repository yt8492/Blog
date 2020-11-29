package com.yt8492.blog.server.adapter.db

import com.yt8492.blog.server.adapter.db.table.EntryTable
import com.yt8492.blog.server.adapter.db.table.EntryTagTable
import com.yt8492.blog.server.adapter.db.table.TagTable
import com.yt8492.blog.server.adapter.db.table.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DBHelper(
    private val dbDriver: String,
    private val dbUrl: String,
    private val dbUserName: String,
    private val dbPassword: String
) {

    fun initDB() {
        Database.connect(dataSource())
        transaction {
            SchemaUtils.createMissingTablesAndColumns(EntryTable, TagTable, EntryTagTable, UserTable)
        }
    }

    private fun dataSource(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = dbDriver
            jdbcUrl = dbUrl
            username = dbUserName
            password = dbPassword
        }
        return HikariDataSource(config)
    }
}
