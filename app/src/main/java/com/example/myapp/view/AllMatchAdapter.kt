package com.example.myapp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Venues

class AllMatchAdapter(context: Context, var mTask: TaskHandler) :
    RecyclerView.Adapter<AllMatchAdapter.AllMatchViewHolder>() {

    var arrayPlaceList: ArrayList<Venues> = ArrayList()
    fun setItem(arrayPlaceList: ArrayList<Venues>) {
        this.arrayPlaceList.clear()
        this.arrayPlaceList.addAll(arrayPlaceList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMatchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.all_matches_items, parent, false)
        return AllMatchViewHolder(view)
    }

    override fun getItemCount(): Int {

        Log.e("size", "" + arrayPlaceList.size)
        return arrayPlaceList.size

    }

    override fun onBindViewHolder(holder: AllMatchViewHolder, position: Int) {

        this.mTask = mTask
        var venues = arrayPlaceList[position]
        if (venues.isSaved) {
            holder.img.setImageResource(R.drawable.ic_star_yellow_24dp)
        } else {
            holder.img.setImageResource(R.drawable.ic_star_border_black_24dp)
        }
        holder.namePlace.text = venues.name
        holder.placeId.text = venues.id
        //holder.address.text=venues.address
        holder.img.setOnClickListener(View.OnClickListener {
            if (venues.isSaved) {
                mTask.enableClick(arrayPlaceList[position], position, false)
                holder.img.setImageResource(R.drawable.ic_star_border_black_24dp)
            } else {
                mTask.enableClick(arrayPlaceList[position], position, true)
                holder.img.setImageResource(R.drawable.ic_star_yellow_24dp)
            }


        })


    }


    class AllMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img = itemView.findViewById<ImageView>(R.id.img1)
        var namePlace = itemView.findViewById<TextView>(R.id.tv_name)
        var placeId = itemView.findViewById<TextView>(R.id.tv_id)


    }
}

interface TaskHandler {
    fun enableClick(venues: Venues, position: Int, isSaved: Boolean)
    //fun disableClick(list2: ArrayList<Venues>,position: Int,disable:Boolean)

}
