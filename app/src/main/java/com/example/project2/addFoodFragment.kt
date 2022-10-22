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
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//
private const val TAG = "addFoodFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [addFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addFoodFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val mylist = mutableListOf<DisplayFood>()
    private lateinit var listsRv: RecyclerView
    private lateinit var adapter: ListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        adapter = ListAdapter(mylist)

        val view = inflater.inflate(R.layout.fragment_add_food, container, false)



        listsRv = view.findViewById(R.id.listsRv)
        listsRv.adapter = adapter
        listsRv.layoutManager = LinearLayoutManager(context)
//
//        lifecycleScope.launch {
//            (activity?.application as ArticleApplication).db.foodDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    DisplayFood(
//                        entity.foodName,
//                        entity.calories
//                    )
//                }.also { mappedList ->
//                    mylist.clear()
//                    mylist.addAll(mappedList)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//        }

//        val layoutManager = LinearLayoutManager(context)
//        foodsRelativeView = view.findViewById(R.id.relView)
//        avgCal = view.findViewById(R.id.avgCal)
//        avgCalNum = view.findViewById(R.id.avgCalNum)


        return view
    }
    private fun fetchData(){
        lifecycleScope.launch {
            (activity?.application as ArticleApplication).db.foodDao().getAll().collect { databaseList ->
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
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchData()
    }

    companion object {

        fun newInstance(): addFoodFragment{
            return addFoodFragment()
        }

    }
}