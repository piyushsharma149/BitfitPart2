package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private val Llists: List<DisplayFood>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        val lis = Llists.get(position)
//        viewholder.item.setText(lis.itemname)
//        viewholder.storename.setText(lis.storename)
//        viewholder.price.setText(lis.price)
        viewholder.item.text = lis.food
        //viewholder.storename.text = lis.storename
        viewholder.price.text = lis.calories
    }

    override fun getItemCount(): Int {
        return Llists.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: TextView
        val storename: TextView
        val price: TextView

        init {

            item = itemView.findViewById(R.id.itemText)
            storename = itemView.findViewById(R.id.storeText)
            price = itemView.findViewById(R.id.priceText)
        }
    }


//    fun addItem(ViewModel viewModel) {
//        items.add(viewModel);
//        notifyItemInserted(position);
//    }





}