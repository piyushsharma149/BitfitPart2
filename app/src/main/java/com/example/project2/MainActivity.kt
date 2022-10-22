package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

const val FOOD_EXTRA = "FOOD_EXTRA"
//var mylist: MutableList<DisplayFood> = ArrayList()
val mylist = mutableListOf<DisplayFood>()
val adapter = ListAdapter(mylist)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = LayoutInflater.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<Button>(R.id.button)

//        val itemText = findViewById<TextView>(R.id.itemText)
//        val storeText = findViewById<TextView>(R.id.storeText)
//        val priceText = findViewById<TextView>(R.id.priceText)

        val addFoodFragment: Fragment = addFoodFragment()
        val foodListFragment: Fragment = FoodListFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_dash -> fragment = addFoodFragment
                R.id.nav_log -> fragment = foodListFragment
            }
            replaceFragment(fragment)
            true
        }

        bottomNavigationView.selectedItemId = R.id.nav_dash
        addButton.setOnClickListener{

            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            //intent.putExtra(FOOD_EXTRA, article)
            this@MainActivity.startActivity(intent)

        }

    }
    private fun replaceFragment(foodListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, foodListFragment)
        fragmentTransaction.commit()
    }

}