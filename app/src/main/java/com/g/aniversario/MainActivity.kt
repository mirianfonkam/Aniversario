package com.g.aniversario

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var birthday : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<TextView>(R.id.btn)
        val inName = findViewById<TextView>(R.id.in_name)
        val inPresente = findViewById<TextView>(R.id.in_presente)
        val inDate = findViewById<TextView>(R.id.in_date)


        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        inDate.setOnClickListener{
            val datePickerDialog = DatePickerDialog(
                this, { view:DatePicker?, year, month, dayOfMonth ->
                    inDate.text = "${dayOfMonth}/${month+1}/${year}" },
            mYear, mMonth, mDay)
            datePickerDialog.show()
        }



        btn.setOnClickListener {
            birthday = inDate.text.toString()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dob = LocalDate.parse(birthday, formatter)
            val today = LocalDate.now()
            val diffInDays = ChronoUnit.DAYS.between(dob,today)

            val usuario = inName?.text
            //val xDias = diffInDays?.toString()
            val presente = inPresente?.text


            Toast.makeText(this, "Olá ${usuario},\n" +
                    "faltam $diffInDays dias para o seu aniversário!\n" +
                    "Espero que você ganhe um ${presente}", Toast.LENGTH_LONG
            ).show()
        }

    }


}


