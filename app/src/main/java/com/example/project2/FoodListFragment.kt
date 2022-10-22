package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//
private const val TAG = "ArticleListFragment"

class FoodListFragment : Fragment() {

    private val foods = mutableListOf<DisplayFood>()
    private lateinit var foodsRelativeView: RelativeLayout
    private lateinit var avgCal: TextView
    private lateinit var avgCalNum: TextView
    private lateinit var listAdapter: ListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_food_list, container, false)

        val layoutManager = LinearLayoutManager(context)
        foodsRelativeView = view.findViewById(R.id.relView)
        avgCal = view.findViewById(R.id.avgCal)
        avgCalNum = view.findViewById(R.id.avgCalNum)



        //foodsRelativeView.layoutManager = layoutManager
        //foodsRelativeView.setHasFixedSize(true)
        //listAdapter = ListAdapter(foods)
        //foodsRelativeView.adapter = articleAdapter

        return view
    }

    private fun fetchAvg(){
        lifecycleScope.launch(                                                                                                                                        IO) {
            avgCal.text = (activity?.application as ArticleApplication).db.foodDao().getAverage().toString().also { adapter.notifyDataSetChanged() }
                }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchAvg()
    }
    companion object {

        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}