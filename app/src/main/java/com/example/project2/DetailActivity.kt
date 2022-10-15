package com.example.project2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"
//var l: MutableList<Lists> = ArrayList()

class DetailActivity : AppCompatActivity() {
//    private lateinit var mediaImageView: ImageView
//    private lateinit var titleTextView: TextView
//    private lateinit var bylineTextView: TextView
//    private lateinit var abstractTextView: TextView
    private lateinit var foodNameInput: EditText
    private lateinit var CaloriesInput: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
//        mediaImageView = findViewById(R.id.mediaImage)
//        titleTextView = findViewById(R.id.mediaTitle)
//        bylineTextView = findViewById(R.id.mediaByline)
//        abstractTextView = findViewById(R.id.movieTitle)
        foodNameInput = findViewById(R.id.foodName)
        CaloriesInput = findViewById(R.id.Calories)
        submitButton = findViewById(R.id.submit)


        var foodstr = ""
        var caloriestr = ""

        // TODO: Get the extra from the Intent
        //val article = intent.getSerializableExtra(FOOD_EXTRA) as DisplayArticle

        // TODO: Set the title, byline, and abstract information from the article
//        titleTextView.text = article.poster_path?

        submitButton.setOnClickListener {
            foodstr = foodNameInput.text.toString()
            caloriestr = CaloriesInput.text.toString()
            //storeStr = storeInput.text.toString()

//            itemText.text = itemStr
//
//            priceText.text = priceStr
//            storeText.text = storeStr
            val list = DisplayFood(foodstr, caloriestr)
            val foodlist = Foods(foodstr, caloriestr)
            //list.let{ mylist ->
            lifecycleScope.launch(IO) {
                (application as ArticleApplication).db.foodDao().deleteAll()
                (application as ArticleApplication).db.foodDao().insert(
                    FoodEntity(
                        foodName = foodlist.foodname,
                        calories = foodlist.calories
                    )
                )
            }

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

            //mylist.add(list)
            foodNameInput.setText("")
            CaloriesInput.setText("")

            val i = Intent(this@DetailActivity, MainActivity::class.java)
            //i.putExtra(FOOD_EXTRA, l)
            //adapter.notifyDataSetChanged()
            startActivity(i)
            //storeInput.setText("")
        }





//        titleTextView.text = article.poster_path
//
////        bylineTextView.text = article.overview?.original
//        bylineTextView.text = article.overview
//        abstractTextView.text = article.title
//        // TODO: Load the media image
//        Glide.with(this)
//            .load(article.backdroppathurl)
//            .into(mediaImageView)
    }
}