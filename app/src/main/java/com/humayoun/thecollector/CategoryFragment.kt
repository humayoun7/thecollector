package com.humayoun.thecollector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.Category
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.shared.SharedViewModel
import com.humayoun.thecollector.ui.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CategoryFragment : Fragment(), CategoryAdapter.CategoryClickListner {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var navController: NavController


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_category, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        initUI()

    }

    fun initUI () {
        setNavBar()

        val categoryDao = CollectorDatabase.getCollectorDatabase(activity?.applicationContext!!).categoryDao()
        CoroutineScope(Dispatchers.IO).launch {
//            categoryDao.insertCategory(Category("Home"))
//            categoryDao.insertCategory(Category("Work2"))
            val list = categoryDao.getAll()
//            val list = listOf<Category>(Category("Home"), Category("Work"))
            withContext(Dispatchers.Main) {
                val categoryAdapter = CategoryAdapter(requireContext(),list, this@CategoryFragment)
                val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                rv_categories.layoutManager = layoutManager
                rv_categories.adapter = categoryAdapter
            }
        }

        fab_add_category.setOnClickListener{
            navController.navigate(R.id.action_CategoryFragment_to_addCategoryDialogFragment)
        }

    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        Utils.setActionBar(requireActivity(), getString(R.string.Categories),false)
    }

    override fun onCategoryItemClick(category: Category) {
        print("clicked")
        // Toast.makeText(requireContext(), "clicked ${category.name}", Toast.LENGTH_LONG).show()
        val bundle = bundleOf(Constants.CATEGORY_NAME to category.name)
        navController.navigate(R.id.action_nav_to_items, bundle)
//        Snackbar.make(currentView, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
    }
}
