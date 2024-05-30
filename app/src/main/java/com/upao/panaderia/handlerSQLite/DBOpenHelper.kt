package com.upao.panaderia.handlerSQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLES().CREATE_TABLE_PRODUCT)
        db.execSQL(TABLES().CREATE_TABLE_CATEGORY)
        db.execSQL(TABLES().CREATE_TABLE_ORDER)
        db.execSQL(TABLES().CREATE_TABLE_ORDERDETAILS)
        db.execSQL(TABLES().CREATE_TABLE_USER)
        db.execSQL(TABLES().CREATE_TABLE_POINTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_PRODUCT)
        db.execSQL(TABLES().CREATE_TABLE_PRODUCT)

        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_CATEGORY)
        db.execSQL(TABLES().CREATE_TABLE_CATEGORY)

        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_ORDER)
        db.execSQL(TABLES().CREATE_TABLE_ORDER)

        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_ORDERDETAILS)
        db.execSQL(TABLES().CREATE_TABLE_ORDERDETAILS)

        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_USER)
        db.execSQL(TABLES().CREATE_TABLE_USER)

        db.execSQL("DROP TABLE IF EXISTS " + TABLES().TABLE_POINTS)
        db.execSQL(TABLES().CREATE_TABLE_POINTS)

        onCreate(db)
    }

    companion object {
        val DATABASE_NAME = "panaderia.db"
        val DATABASE_VERSION = 1
    }
}