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

    var mYear = 1111
    var mMonth = 11
    var mDay = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<TextView>(R.id.btn)
        val in_name = findViewById<TextView>(R.id.in_name)
        val in_present = findViewById<TextView>(R.id.in_presente)
        val in_date = findViewById<TextView>(R.id.in_date)


        val c = Calendar.getInstance()
        var mYear = c.get(Calendar.YEAR);
        var mMonth = c.get(Calendar.MONTH);
        var mDay = c.get(Calendar.DAY_OF_MONTH);

        in_date.setOnClickListener{
            val datePickerDialog = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { view:DatePicker?, year, month, dayOfMonth ->
                    in_date.text = "" + dayOfMonth + "/" + (month + 1) + "/" + year },
            mYear, mMonth, mDay)
            datePickerDialog.show()
        }

       // val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        //var birthday = LocalDate.parse(in_date.text, DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val today = LocalDate.now()
        val diffInDays = ChronoUnit.DAYS.between(birthday, today)


        btn.setOnClickListener {
            val usuario = in_name?.text
            val xDias = diffInDays?.toString()
            val presente = in_present?.text


            Toast.makeText(this, "Olá ${usuario},\n" +
                    "faltam ${xDias} dias para o seu aniversário!\n" +
                    "Espero que você ganhe um ${presente}", Toast.LENGTH_LONG
            ).show()
        }

    }


}


