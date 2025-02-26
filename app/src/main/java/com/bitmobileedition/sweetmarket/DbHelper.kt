package com.bitmobileedition.sweetmarket

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "SweetMarket.db", factory, 1){ // ? - для корректной обработки пустых значений

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE buyer_auth (id INT PRIMARY KEY, buyer_login TEXT, buyer_email TEXT, buyer_password TEXT)" //строка
        db!!.execSQL(query) //обращение к бд для выполнения строки как команды (!! - корректная обработка NULL
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS buyer_auth")
        onCreate(db) //пересоздание бд
    }

    fun addUser(user: User) { //регистрация пользователя в бд
        val values = ContentValues() // сюда подставляем айди и значения, которые будут обрабатываться SQL-командой
        values.put("buyer_login", user.buyer_login)
        values.put("buyer_email", user.buyer_email)
        values.put("buyer_password", user.buyer_password)

        val db = this.writableDatabase
        db.insert("buyer_auth", null, values) // в поля будут добавлены полученные значения

        db.close()
    }

    //получение логина и пароля
    @SuppressLint("Recycle")
    fun getUser(buyer_login: String, buyer_password: String) : Boolean {
        val db = this.readableDatabase
        //проверка полученных логина и пароля
        val result = db.rawQuery("SELECT * FROM buyer_auth WHERE buyer_login = '$buyer_login' AND buyer_password = '$buyer_password'", null)
        return result.moveToFirst() //берем первую запись, есть - true, нет - false
    }
}