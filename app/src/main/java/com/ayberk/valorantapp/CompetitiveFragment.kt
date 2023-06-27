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
import com.ayberk.valorantapp.adapter.CompetitiveAdapter
import com.ayberk.valorantapp.databinding.FragmentCompetitiveBinding
import com.ayberk.valorantapp.models.competitive.Competitive
import com.ayberk.valorantapp.models.competitive.Data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitiveFragment : Fragment() {

    private var _binding : FragmentCompetitiveBinding? = null
    private val binding get() = _binding!!
    private val viewCompetitive: AgendsViewModel by viewModels()
    private lateinit var competitiveAdapter: CompetitiveAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompetitiveBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        initRecyclerViews()
        fetchCompetitive()

        viewCompetitive.getCompetitive().observe(viewLifecycleOwner, { t: Competitive? ->
            t?.let { competitive ->
                val tiers = competitive.data?.get(4)?.tiers
                tiers?.let {
                    competitiveAdapter.setLists(it)
                }
            }
        })


        return view
    }

    fun initRecyclerViews(){

        competitiveAdapter = CompetitiveAdapter()
        binding.recyclerView.adapter = competitiveAdapter

    }

    fun fetchCompetitive() {
        CoroutineScope(Dispatchers.Main).launch {
            viewCompetitive.loadCompetitive()
        }
    }
}

