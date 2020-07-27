package com.humayoun.thecollector.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.Constants
import com.humayoun.thecollector.R
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.shared.SharedRepository
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.*


class CategoryFragment : Fragment(), CategoryAdapter.CategoryClickListner {

    private lateinit var navController: NavController


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    fun setupUI () {
        setNavBar()

        fab_add_category.setOnClickListener{
            navController.navigate(R.id.action_CategoryFragment_to_addCategoryDialogFragment)
        }

        val list = SharedRepository.getInstance(requireContext()).getAllCategories()
        val categoryAdapter = CategoryAdapter(list, this)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)

        rv_categories.layoutManager = layoutManager
        rv_categories.adapter = categoryAdapter

//        val categoryDao = CollectorDatabase.getCollectorDatabase(activity?.applicationContext!!).categoryDao()
//        CoroutineScope(Dispatchers.IO).launch {
//            val list = categoryDao.getAll()
//            withContext(Dispatchers.Main) {
//                val categoryAdapter = CategoryAdapter(list, this@CategoryFragment)
//                val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
//                rv_categories.layoutManager = layoutManager
//                rv_categories.adapter = categoryAdapter
//            }
//        }
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        Utils.setActionBar(requireActivity(), getString(R.string.Categories),false)
    }

    override fun onCategoryItemClick(category: Category) {
        val bundle = bundleOf(Constants.CATEGORY_NAME to category.name)
        navController.navigate(R.id.action_nav_to_items, bundle)
    }
}
