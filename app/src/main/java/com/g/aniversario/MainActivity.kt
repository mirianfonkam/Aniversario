package com.g.aniversario

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.text.*


class MainActivity : AppCompatActivity() {
    //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/
    var users = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSend = findViewById<TextView>(R.id.btn)
        val inName = findViewById<TextView>(R.id.in_name)
        val inPresente = findViewById<TextView>(R.id.in_presente)
        val inDate = findViewById<TextView>(R.id.in_date)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        var  userList = ""
        for (i in users){
            userList += i + "\n"
        }

        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        fab.setOnClickListener{
            val intent = Intent(this, ScrollingUsers::class.java)
            intent.putExtra("extra_text", userList);
            startActivity(intent)
        }

        inDate.setOnClickListener{
            val datePickerDialog = DatePickerDialog(
                this, { view:DatePicker?, year, month, dayOfMonth ->
                    //note: month has an initial index of 0 (Jan)
                    var m = month + 1
                    //DONE: leading 0, issues with pattern dd/MM/yyyy format
                    var fMonth = String.format("%02d", m)
                    var fDay = String.format("%02d", dayOfMonth)
                    //now day and month have leading 0's
                    inDate.text = "${fDay}/${fMonth}/${year}" },
            mYear, mMonth, mDay)
            datePickerDialog.show()
        }

        btnSend.setOnClickListener {
            var dateOfBirth = inDate.text.toString()
            //alt-change to d/M/yyyy
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dob = LocalDate.parse(dateOfBirth, formatter)
            val today = LocalDate.now()
            //val age = ChronoUnit.YEARS.between(dob, today)

            val xDays = calcDaysUntilBday(dob, today)
            val nome = inName?.text.toString()
            val presente = inPresente?.text.toString()
            //CLASS
            users.add(User(nome,xDays,presente).toString())

            val message = "Olá ${nome},\n" +
                    "faltam ${xDays} dias para o seu aniversário!\n" +
                    "Espero que você ganhe um ${presente}"

            Toast.makeText(this, message, Toast.LENGTH_LONG
            ).show()
            // Issues #1 Handling Wrong User Input e.g "" or " ", or dates that are greater than the current date
            // Issued #3 Handling horizontal view//scroll feature
            // Feature #1 coleção a string que retorna informando nome, dias restantes para o aniversário da pessoa bem como presente esperado.
        }

    }

    // funcao de Calculo de Dias Para Aniversario (Recebe data de nasc e data hoje)
    fun calcDaysUntilBday(dob: LocalDate, today: LocalDate): Long {
        var nextBirthday = LocalDate.of(today.year, dob.month, dob.dayOfMonth)
        if (nextBirthday < today) {
            //aniversario ja passou, adicione um ano
            nextBirthday = nextBirthday.plusYears(1)
        }
        var diffInDays = ChronoUnit.DAYS.between(today, nextBirthday)
        return(diffInDays)
    }
}




