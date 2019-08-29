package com.example.myapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Venues
import com.example.myapp.viewmodel.MatchViewModel

class FragmentSave : Fragment(){

    lateinit var vm : MatchViewModel
    lateinit var adapter:SaveMatchAdapter

    companion object{

        fun getInstance():FragmentSave{

            return FragmentSave()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm=ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view=inflater.inflate(R.layout.save_matches,container,false)

        var recycler=view.findViewById<RecyclerView>(R.id.recyclerSave)
        var layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)


        adapter= SaveMatchAdapter(requireContext(),object : SaveMatchAdapter.Task{

            override fun clickDel(venuesList: ArrayList<Venues>,pos:Int,isSaved:Boolean) {
                //vm.deletePlace(venues.id)
               var venues=venuesList[pos]
                venues.isSaved = isSaved
                vm.updatePlace(venues.id,isSaved)
                venuesList.remove(venues)


            }
        })

        recycler.layoutManager=layoutManager
        recycler.adapter = adapter
        vm.saveMatches.observe(this,Observer{

            it->
            adapter.setItem(it as ArrayList<Venues>)
            adapter.notifyDataSetChanged()


        })

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

}