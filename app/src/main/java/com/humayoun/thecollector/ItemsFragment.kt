package com.humayoun.thecollector

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
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.data.Item
import com.humayoun.thecollector.ui.CategoryAdapter
import com.humayoun.thecollector.ui.ItemAdpater
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ItemsFragment : Fragment(), ItemAdpater.ItemClickListner {

    private lateinit var navController: NavController
    private var categoryName: String? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_items, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
        categoryName = arguments?.get(Constants.CATEGORY_NAME).toString()
        setupUI()

        Toast.makeText(activity,"fragement created for items", Toast.LENGTH_LONG)
//        val list = listOf<Item>(Item("test","testing again", 4.5f),Item("test","testing again", 4.5f),
//            Item("test","testing again", 4.5f),Item("test","testing again", 4.5f))
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        setHasOptionsMenu(true)
        Utils.setActionBar(requireActivity(), getString(R.string.items),true)
    }

    fun setupUI() {
        setNavBar()
        val itemDao = CollectorDatabase.getCollectorDatabase(activity?.applicationContext!!).ItemDao()
        CoroutineScope(Dispatchers.IO).launch {
            categoryName?.let {
                val list = itemDao.getItemsForCategory(it)
                withContext(Dispatchers.Main) {
                    val itemsAdapter = ItemAdpater(requireContext(), list, this@ItemsFragment)
                    val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    rv_items.layoutManager = layoutManager
                    rv_items.adapter = itemsAdapter
                }
            }


        }

        fab_add_item.setOnClickListener{
            val bundle = bundleOf(Constants.CATEGORY_NAME to categoryName)
            navController.navigate(R.id.action_ItemsFragment_to_addItemFragment, bundle)
        }
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
