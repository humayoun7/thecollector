package com.humayoun.thecollector.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.Constants
import com.humayoun.thecollector.R
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.data.item.Item
import com.humayoun.thecollector.shared.SharedRepository
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemsFragment : Fragment(), ItemAdpater.ItemClickListner {

    private lateinit var navController: NavController
    private var categoryName: String? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setReceivingArguments()
        setupUI()
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment
        )
        setHasOptionsMenu(true)
        Utils.setActionBar(requireActivity(), getString(R.string.items),true)
    }

    fun setupUI() {
        setNavBar()

        fab_add_item.setOnClickListener{
            val bundle = bundleOf(Constants.CATEGORY_NAME to categoryName)
            navController.navigate(R.id.action_ItemsFragment_to_addItemFragment, bundle)
        }
        
        categoryName?.let {
            val list = SharedRepository.getInstance(requireContext()).getAllItemsForCategory(it)
            val itemsAdapter = ItemAdpater( requireContext(), list, this)
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rv_items.layoutManager = layoutManager
            rv_items.adapter = itemsAdapter
        }

//        val itemDao = CollectorDatabase.getCollectorDatabase(activity?.applicationContext!!).ItemDao()
//        CoroutineScope(Dispatchers.IO).launch {
//            categoryName?.let {
//                val list = itemDao.getItemsForCategory(it)
//                withContext(Dispatchers.Main) {
//                    val itemsAdapter = ItemAdpater( requireContext(), list, this@ItemsFragment)
//                    val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//                    rv_items.layoutManager = layoutManager
//                    rv_items.adapter = itemsAdapter
//                }
//            }
//        }
    }

    fun setReceivingArguments() {
        categoryName = arguments?.get(Constants.CATEGORY_NAME).toString()
    }

    override fun onItemClick(item: Item) {
        navController.navigate(R.id.action_ItemsFragment_to_itemDetailsFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}
