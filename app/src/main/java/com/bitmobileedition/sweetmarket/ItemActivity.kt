package com.bitmobileedition.sweetmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.item_list_title1)
        val text: TextView = findViewById(R.id.item_list_text)
        val imageView: ImageView = findViewById(R.id.item_list_image)

        title.text = intent.getStringExtra("itemTitle")
        Glide.with(this).load("http://84.246.85.148:8080/${intent.getStringExtra("itemImage")}").into(imageView)
        text.text = intent.getStringExtra("itemText")

    }
}