package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var l: MutableList<Lists> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemInput = findViewById<EditText>(R.id.itemName)
        val priceInput = findViewById<EditText>(R.id.price)
        val storeInput = findViewById<EditText>(R.id.storeName)
        val submit = findViewById<Button>(R.id.submit)
        val listsRv = findViewById<RecyclerView>(R.id.listsRv)

//        val itemText = findViewById<TextView>(R.id.itemText)
//        val storeText = findViewById<TextView>(R.id.storeText)
//        val priceText = findViewById<TextView>(R.id.priceText)



        var itemStr = ""
        var priceStr = ""
        var storeStr = ""
        //var templist: MutableList<Lists> = ArrayList()

        //val adapter = ListAdapter(l)
        val adapter = ListAdapter(l)
        listsRv.adapter = adapter
        listsRv.layoutManager = LinearLayoutManager(this)

        submit.setOnClickListener {
            itemStr = itemInput.text.toString()
            priceStr = priceInput.text.toString()
            storeStr = storeInput.text.toString()

//            itemText.text = itemStr
//
//            priceText.text = priceStr
//            storeText.text = storeStr
            val list = Lists(itemStr, priceStr, storeStr)
            l.add(list)


            adapter.notifyDataSetChanged()
            itemInput.setText("")
            priceInput.setText("")
            storeInput.setText("")


        }
    }

}