package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate = findViewById(R.id.selected_date)
        tvMinutes = findViewById(R.id.counted_minutes)
        var selectedDate: String
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        selectedDate = "$day/${month + 1}/$year"
        tvSelectedDate?.text = selectedDate

        val btnDatePicker = findViewById<Button>(R.id.btn_date_picker)

        btnDatePicker.setOnClickListener {
            datePicker()
        }
    }

    fun datePicker() {
        var selectedDate: String
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        selectedDate = "$day/${month + 1}/$year"
        DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)
                val selectedDateMinutes = theDate.time / 60000
                val currentDate =
                    simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000
                val differanceMinutes = currentDateInMinutes - selectedDateMinutes
                tvMinutes?.text = differanceMinutes.toString()
            },
            year,
            month,
            day
        ).show()
    }
}