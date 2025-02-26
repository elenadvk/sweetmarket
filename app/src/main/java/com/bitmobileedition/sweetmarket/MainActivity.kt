package com.bitmobileedition.sweetmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)

        val button: Button = findViewById(R.id.button_reg)

        //переменная, отвечающая за переход между окнами авторизации и регистрации
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        //обработчик нажатия текста
        linkToAuth.setOnClickListener {
            //создание переменной для перехода с указанием страницы, на которую переходим
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }


        button.setOnClickListener {
            val buyer_login = userLogin.text.toString().trim() //получение текста от пользователя при нажатии на кнопку (trim() - удаление пробелов)
            val buyer_email = userEmail.text.toString().trim()
            val buyer_password = userPass.text.toString().trim()

            if (buyer_login == "" || buyer_email == "" || buyer_password == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show() //всплывающая подсказка (LENGTH_LONG - насколько длинная подсказка)
            else{
                val user = User(buyer_login, buyer_email, buyer_password)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $buyer_login добавлен", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }
    }
}