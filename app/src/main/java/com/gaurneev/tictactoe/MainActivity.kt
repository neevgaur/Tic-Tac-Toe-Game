package com.gaurneev.tictactoe

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.gaurneev.tictactoe.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity(){
    private var mainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        mainBinding?.btnSt?.setOnClickListener {
            val intent = Intent(this,game::class.java)
            startActivity(intent)
        }
    }
}
