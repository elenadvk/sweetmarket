package com.bitmobileedition.sweetmarket

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "db_app", factory, 1){ // ? - для корректной обработки пустых значений

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)" //строка
        db!!.execSQL(query) //обращение к бд для выполнения строки как команды (!! - корректная обработка NULL
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db) //пересоздание бд
    }

    fun addUser(user: User) { //регистрация пользователя в бд
        val values = ContentValues() // сюда подставляем айди и значения, которые будут обрабатываться SQL-командой
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values) // в поля будут добавлены полученные значения

        db.close()
    }

    //получение логина и пароля
    fun getUser(login: String, pass: String) : Boolean {
        val db = this.readableDatabase
        //проверка полученных логина и пароля
        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst() //берем первую запись, есть - true, нет - false
    }
}