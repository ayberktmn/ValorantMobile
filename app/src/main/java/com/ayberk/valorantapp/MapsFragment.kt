package com.ayberk.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.valorantapp.ViewModel.AgendsViewModel
import com.ayberk.valorantapp.adapter.AgendsAdapter
import com.ayberk.valorantapp.adapter.MapsAdapter
import com.ayberk.valorantapp.databinding.FragmentHomeBinding
import com.ayberk.valorantapp.databinding.FragmentMapsBinding
import com.ayberk.valorantapp.models.Agends
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapsAdapter: MapsAdapter
    private val viewDetailsModel: AgendsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rcylerMaps.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        initRecyclerViews()
        fetchAgents()

        viewDetailsModel.getMaps().observe(viewLifecycleOwner,object :
            Observer<com.ayberk.valorantapp.models.maps.Map> {
            override fun onChanged(t: com.ayberk.valorantapp.models.maps.Map?) {
                //  progressDialog.hide()
                if (t != null) {
                    mapsAdapter.setLists(t.data)
                }
            }
        })

        return view
    }


    fun initRecyclerViews(){

        mapsAdapter = MapsAdapter()
        binding.rcylerMaps.adapter = mapsAdapter

    }

    fun fetchAgents() {
        CoroutineScope(Dispatchers.Main).launch {
            viewDetailsModel.loadMaps("")
        }
    }
}