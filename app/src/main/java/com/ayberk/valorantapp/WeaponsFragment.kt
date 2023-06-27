package com.ayberk.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.valorantapp.ViewModel.AgendsViewModel
import com.ayberk.valorantapp.adapter.WeaponsAdapter
import com.ayberk.valorantapp.databinding.FragmentWeaponsBinding
import com.ayberk.valorantapp.models.Agends.DataX
import com.ayberk.valorantapp.models.Agends.Weapons
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeaponsFragment : Fragment() {

    private var _binding: FragmentWeaponsBinding? = null
    private val binding get() = _binding!!
    private lateinit var WeaponsAdapter: WeaponsAdapter
    lateinit var resultList: DataX

    val viewWeaponsModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(AgendsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeaponsBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.recyclerViewWeapons.layoutManager = LinearLayoutManager(requireContext())
        initRecyclerViews()
        fetchWeapons()

        viewWeaponsModel.getObserverLiveDataWeapons().observe(viewLifecycleOwner, object : Observer<Weapons> {
            override fun onChanged(t: Weapons?) {
                if(t != null){
                    WeaponsAdapter.setListsWeapons(t.data)

                    }
                }
           })

        return view
    }

    fun initRecyclerViews(){

        WeaponsAdapter = WeaponsAdapter()
        binding.recyclerViewWeapons.adapter = WeaponsAdapter

    }

    fun fetchWeapons() {
        CoroutineScope(Dispatchers.IO).launch {
            viewWeaponsModel.loadWeapons("")
        }
    }

}