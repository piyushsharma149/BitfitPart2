package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

const val FOOD_EXTRA = "FOOD_EXTRA"
//var mylist: MutableList<DisplayFood> = ArrayList()
val mylist = mutableListOf<DisplayFood>()
val adapter = ListAdapter(mylist)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemInput = findViewById<EditText>(R.id.itemName)
        val priceInput = findViewById<EditText>(R.id.Calories)
        val storeInput = findViewById<EditText>(R.id.foodName)
        val submit = findViewById<Button>(R.id.submit)
        val listsRv = findViewById<RecyclerView>(R.id.listsRv)
        val addButton = findViewById<Button>(R.id.button)

//        val itemText = findViewById<TextView>(R.id.itemText)
//        val storeText = findViewById<TextView>(R.id.storeText)
//        val priceText = findViewById<TextView>(R.id.priceText)



        var itemStr = ""
        var priceStr = ""
        var storeStr = ""
        //var templist: MutableList<Lists> = ArrayList()

        //val adapter = ListAdapter(l)

        listsRv.adapter = adapter
        listsRv.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            (application as ArticleApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.calories
                    )
                }.also { mappedList ->
                    mylist.clear()
                    mylist.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        addButton.setOnClickListener{


            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            //intent.putExtra(FOOD_EXTRA, article)
            this@MainActivity.startActivity(intent)

        }








//        submit.setOnClickListener {
//            itemStr = itemInput.text.toString()
//            priceStr = priceInput.text.toString()
//            storeStr = storeInput.text.toString()
//
////            itemText.text = itemStr
////
////            priceText.text = priceStr
////            storeText.text = storeStr
//            val list = Lists(itemStr, priceStr, storeStr)
//            l.add(list)
//
//
//            adapter.notifyDataSetChanged()
//            itemInput.setText("")
//            priceInput.setText("")
//            storeInput.setText("")
//
//
//        }
    }

}