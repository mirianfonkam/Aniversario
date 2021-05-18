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
    lateinit var dateOfBirth : String

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
            dateOfBirth = inDate.text.toString()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dob = LocalDate.parse(dateOfBirth, formatter)
            val today = LocalDate.now()
            val age = ChronoUnit.YEARS.between(dob, today)

            var nextBirthday = LocalDate.of(today.year, dob.month, dob.dayOfMonth)

            if (nextBirthday < today) {
                //aniversario ja passou
                nextBirthday = nextBirthday.plusYears(1)
            }

            val diffInDays = ChronoUnit.DAYS.between(today, nextBirthday)


            val usuario = inName?.text
            //val xDias = diffInDays?.toString()
            val presente = inPresente?.text


            Toast.makeText(this, "Olá ${usuario},\n" +
                    "faltam $diffInDays dias para o seu aniversário!\n" +
                    "Espero que você ganhe um ${presente}", Toast.LENGTH_LONG
            ).show()



            // Issues #1 Handling Wrong User Input e.g "" or " ", or dates that are greater than the current date
            // Issues #2 User has birthday has passed
            // Issued #3 Handling horizontal view//scroll feature

            // Feature #1 coleção a string que retorna informando nome, dias restantes para o aniversário da pessoa bem como presente esperado.


        }

    }


}


