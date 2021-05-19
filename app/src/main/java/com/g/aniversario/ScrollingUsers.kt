package com.g.aniversario

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.g.aniversario.databinding.ScrollUsersBinding

class ScrollingUsers : AppCompatActivity() {

    private lateinit var binding: ScrollUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textOut = findViewById<TextView>(R.id.userTxt)

        val usersList = intent.getStringExtra("extra_text")
        //textOut.text = usersList

        binding = ScrollUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}