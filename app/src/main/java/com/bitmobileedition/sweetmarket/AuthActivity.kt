package com.bitmobileedition.sweetmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPass: EditText = findViewById(R.id.user_pass_auth)
        val button: Button = findViewById(R.id.button_auth)

        //переменная, отвечающая за переход между окнами авторизации и регистрации
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        //обработчик нажатия текста
        linkToReg.setOnClickListener {
            //создание переменной для перехода с указанием страницы, на которую переходим
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim() //получение текста от пользователя при нажатии на кнопку (trim() - удаление пробелов)
            val pass = userPass.text.toString().trim()

            if (login == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show() //всплывающая подсказка (LENGTH_LONG - насколько длинная подсказка)
            else{

                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, pass)

                if (isAuth){
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    //переход к другой активити при успешной авторизации
                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_LONG).show()
            }
        }
    }
}