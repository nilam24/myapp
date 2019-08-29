package com.example.myapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Venues

class SaveMatchAdapter(context:Context,var mTask:Task) : RecyclerView.Adapter<SaveMatchAdapter.SaveMatchViewHolder>() {

    var itemList : ArrayList<Venues> = ArrayList()

    fun setItem(itemList:ArrayList<Venues>){
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveMatchViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.save_matches_items,parent,false)

        return SaveMatchViewHolder(view)
    }

    override fun getItemCount(): Int {

        return itemList.size

    }

    override fun onBindViewHolder(holder: SaveMatchViewHolder, position: Int) {

        this.mTask = mTask
        var venues=itemList[position]
        holder.nameText.text = venues.name
        holder.id.text = venues.id
        holder.img.setOnClickListener(View.OnClickListener {
            mTask.clickDel(itemList,position,false)


        } )


    }

    class SaveMatchViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){


        var img=itemView.findViewById<ImageView>(R.id.img2)
        var nameText = itemView.findViewById<TextView>(R.id.tv_name)
        var id = itemView.findViewById<TextView>(R.id.tv_id)

    }

    interface Task{
        fun clickDel(venues: ArrayList<Venues>,position: Int,isSaved:Boolean)
    }
}