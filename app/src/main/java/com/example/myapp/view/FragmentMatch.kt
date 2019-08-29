package com.example.myapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.MyResponse
import com.example.myapp.model.Venues
import com.example.myapp.viewmodel.MatchViewModel

class FragmentMatch : Fragment() {

    lateinit var vm : MatchViewModel
    lateinit var adapter: AllMatchAdapter
    var placeList:ArrayList<Venues> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProviders.of(this).get(MatchViewModel::class.java)

    }

    companion object{

        fun getInstance():FragmentMatch{

            return FragmentMatch()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view= inflater.inflate(R.layout.all_matches,container,false)


            vm.getDetail("40.7484,-73.9857", "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ", "20180616")


        var recyclerPlace=view.findViewById<RecyclerView>(R.id.recyclerAllMatches)
        var layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)

        adapter = AllMatchAdapter(requireContext(),object:TaskHandler{

            override fun enableClick(venues: Venues, position: Int, isSaved: Boolean) {


                if(isSaved) {
                    vm.updatePlace(venues.id, true)

                }
                else {
                    vm.updatePlace(venues.id,false)
                }

            } })

        recyclerPlace.layoutManager=layoutManager
        recyclerPlace.adapter=adapter

        vm.allMatches.observe(this, Observer { it->
            it.let {  adapter.setItem(it as ArrayList<Venues>)
                         adapter.notifyDataSetChanged()
            }


        })

        return view
    }


}