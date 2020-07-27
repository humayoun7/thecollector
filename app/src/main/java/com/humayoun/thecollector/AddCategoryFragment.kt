package com.humayoun.thecollector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.Category
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.ui.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_add_category_dialog.*
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class AddCategoryFragment : DialogFragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    fun setupUI() {
        setNavBar()
        button_add_category.setOnClickListener{
            val cName = et_category.text.toString()

            if(!cName.isEmpty()) {
                val categoryDao = CollectorDatabase.getCollectorDatabase(activity?.applicationContext!!).categoryDao()
                CoroutineScope(Dispatchers.IO).launch {
                    categoryDao.insertCategory(Category(cName))
                }
            }

            navController.navigateUp()
        }
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        setHasOptionsMenu(true)
        Utils.setActionBar(requireActivity(), getString(R.string.add_new_category),true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}
