package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items : MutableList<Item>
    lateinit var itemAdapter : ItemAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val nameEditText = findViewById<EditText>(R.id.itemNameEditText)
        val priceEditText = findViewById<EditText>(R.id.priceEditText)
        val urlEditText = findViewById<EditText>(R.id.urlEditText)

        items = ArrayList()

        submitButton.setOnClickListener() {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            val nameInput = nameEditText.text.toString()
            val priceInput = "$" + priceEditText.text.toString()
            val urlInput = urlEditText.text.toString()

            if(nameInput.length == 0 ||
                priceInput.length == 1 ||
                urlInput.length == 0) {
                Toast.makeText(this, "Incomplete text fields!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            nameEditText.text.clear()
            priceEditText.text.clear()
            urlEditText.text.clear()


            val newItem = Item(nameInput, priceInput, urlInput)

            items.add(newItem)

            itemAdapter = ItemAdapter(items)

            recyclerView.adapter = itemAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        }


    }
}