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
import com.ayberk.valorantapp.adapter.AgendsAdapter
import com.ayberk.valorantapp.databinding.FragmentAgentsBinding
import com.ayberk.valorantapp.databinding.FragmentHomeBinding
import com.ayberk.valorantapp.models.Agends
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AgentsFragment : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var agendsAdapter: AgendsAdapter
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(AgendsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentsBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rcylerAgends.layoutManager = LinearLayoutManager(requireContext())

        initRecyclerViews()
        fetchAgents()

        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<Agends> {
            override fun onChanged(t: Agends?) {
                if(t != null){
                    agendsAdapter.setLists(t.data)
                }
            }
        })

        return view
    }

    fun initRecyclerViews(){

        agendsAdapter = AgendsAdapter()
        binding.rcylerAgends.adapter = agendsAdapter

    }

    fun fetchAgents() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadAgends("")
        }
    }


}