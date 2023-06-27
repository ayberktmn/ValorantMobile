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
import com.ayberk.valorantapp.adapter.CardsAdapter
import com.ayberk.valorantapp.databinding.FragmentCardsBinding
import com.ayberk.valorantapp.models.playerscard.Cards
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardsFragment : Fragment() {

    private var _binding : FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private val viewCompetitive: AgendsViewModel by viewModels()
    private lateinit var cardsAdapter: CardsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rcylerCompetitive.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        initRecyclerViews()
        fetchCompetitive()

        viewCompetitive.getCards().observe(viewLifecycleOwner, object : Observer<com.ayberk.valorantapp.models.playerscard.Cards> {
            override fun onChanged(t: Cards?) {
                if (t != null) {
                    cardsAdapter.setLists(t.data)
                }
            }
        })
        return view
    }

    fun initRecyclerViews(){

        cardsAdapter = CardsAdapter()
        binding.rcylerCompetitive.adapter = cardsAdapter

    }

    fun fetchCompetitive() {
        CoroutineScope(Dispatchers.Main).launch {
            viewCompetitive.loadCards()
        }
    }
}