package com.ayberk.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.valorantapp.ViewModel.AbilityViewModel
import com.ayberk.valorantapp.ViewModel.AgendsViewModel
import com.ayberk.valorantapp.adapter.WeaponSkinsAdapter
import com.ayberk.valorantapp.adapter.WeaponsAdapter
import com.ayberk.valorantapp.databinding.FragmentWeponsDetailsBinding
import com.ayberk.valorantapp.models.DataX
import com.ayberk.valorantapp.models.Skin
import com.ayberk.valorantapp.models.WeaponStats
import com.ayberk.valorantapp.models.Weapons
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeponsDetailsFragment : Fragment() {

    private var _binding: FragmentWeponsDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var weaponsDetailList : DataX
    lateinit var weaponsSkinList : Skin
    private val WeponsDetailsModel: AgendsViewModel by viewModels()
    private lateinit var WeaponskinsAdapter: WeaponSkinsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeponsDetailsBinding.inflate(inflater,container,false)
        val view = binding.root

        initRecyclerViews()
        fetchWeaponsDetail()

        arguments?.let {

            val gelenDetail = WeponsDetailsFragmentArgs.fromBundle(it).weponsDetail

            WeponsDetailsModel.getObserverLiveDataWeapons().observe(viewLifecycleOwner, object :
                Observer<Weapons> {
                override fun onChanged(t: Weapons?) {
                    if(t != null){

                        weaponsDetailList = t.data[gelenDetail]

                        if (weaponsDetailList.defaultSkinUuid == "12cc9ed2-4430-d2fe-3064-f7a19b1ba7c7"){

                            binding.weaponsRole.text = weaponsDetailList.shopData?.category
                            binding.WeponsName.text = weaponsDetailList.displayName

                            binding.txtAbilities.text =
                               "150"
                            binding.txtAbilities2.text =
                                "75"
                            binding.txtAbilities3.text =
                                "50"

                            Glide.with(binding.agentIconImageView)
                                .load(weaponsDetailList.killStreamIcon)
                                .into(binding.agentIconImageView)

                        }else {

                            binding.WeponsName.text = weaponsDetailList.displayName
                            binding.weaponsRole.text = weaponsDetailList.shopData.category

                            Glide.with(binding.agentIconImageView)
                                .load(weaponsDetailList.displayIcon)
                                .into(binding.agentIconImageView)

                            binding.txtAbilities.text =
                                weaponsDetailList.weaponStats.damageRanges.get(0).headDamage.toString()
                            binding.txtAbilities2.text =
                                weaponsDetailList.weaponStats.damageRanges.get(0).bodyDamage.toString()
                            binding.txtAbilities3.text =
                                weaponsDetailList.weaponStats.damageRanges.get(0).legDamage.toString()

                        }
                    }
                }
            })


            WeponsDetailsModel.getWeaponSkin().observe(viewLifecycleOwner, object :
                Observer<com.ayberk.valorantapp.models.skin.Skin> {
                override fun onChanged(t: com.ayberk.valorantapp.models.skin.Skin?) {
                    if (t != null) {

                        WeaponskinsAdapter.setListsWeaponSkin(t.data)
                    }
                }
            })

            binding.backButtonImageView.setOnClickListener {
                findNavController().navigate(R.id.action_weponsDetailsFragment_to_weaponsFragment)
            }
        }
        return view
    }
    fun fetchWeaponsDetail() {
        CoroutineScope(Dispatchers.Main).launch {
            WeponsDetailsModel.loadWeapons("")
            WeponsDetailsModel.loadWeaponsSkin("")
        }
    }

    fun initRecyclerViews(){
        val lmHorizontal = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

       binding.rcylerSkins.layoutManager =lmHorizontal

        WeaponskinsAdapter = WeaponSkinsAdapter()
        binding.rcylerSkins.adapter = WeaponskinsAdapter

    }
}