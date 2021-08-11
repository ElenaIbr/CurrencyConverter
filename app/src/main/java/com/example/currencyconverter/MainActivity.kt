package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.main.Exchange
import com.example.currencyconverter.main.MainViewModel
import com.example.currencyconverter.main.MainViewModelFactory
import com.example.currencyconverter.repository.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    var rate: Double = 0.0
    var date: TextView? = null
    var resVal: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner1 = findViewById<AutoCompleteTextView>(R.id.v_to)
        val edt = findViewById<EditText>(R.id.amount)
        val btn = findViewById<ImageButton>(R.id.resBtn)

        //choose valute
        val valArray = resources.getStringArray(R.array.val_keys)
        val arrayAdapter = ArrayAdapter(this, R.layout.spin_item, resources.getStringArray(R.array.val_keys))

        spinner1.setAdapter(arrayAdapter)

        spinner1.setOnItemClickListener { parent, view, position, id ->
            resVal = valArray[position]
        }

        //convert
        btn.setOnClickListener {

            var double2  = edt.doubleValue()
            Log.d("MyLog", "$double2")

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)

            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getPost()

            viewModel.myResponse.observe(this, Observer { response ->

                if (response.isSuccessful) {

                    date = findViewById(R.id.date)
                    date?.visibility = View.VISIBLE

                    date?.text = response.body()?.Date

                    val array = response.body()?.Valute
                    val filteredItems = array?.filterKeys { it == resVal }
                    filteredItems?.forEach { (t, u) ->
                        rate = u.Value
                        val e = Exchange(double2, rate)

                        val text = findViewById<TextView>(R.id.result)
                        text?.visibility = View.VISIBLE
                        text.text = "$double2 $t = ${e.getResult().toString()} RUB"
                    }
                } else {
                    Log.d("MyLog", response.errorBody().toString())
                }
            })
        }
        }
        private fun EditText.doubleValue() = text.toString().toDoubleOrNull() ?: 0.0
    }
