package com.humayoun.thecollector.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.humayoun.thecollector.R
import com.humayoun.thecollector.utils.Utils
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.category.existsIn
import com.humayoun.thecollector.shared.SharedRepository
import kotlinx.android.synthetic.main.fragment_add_category_dialog.*


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
            if(!isValidInput()) {
                return@setOnClickListener
            }

            val cName = et_category.text.toString()
            et_category.clearFocus()

            val newCategory = Category(cName)
            SharedRepository.getInstance(requireContext()).insertCategory(newCategory)

            navController.navigateUp()
        }
    }


    fun isValidInput(): Boolean {
        val cName = et_category.text.toString()
        if (!Utils.isValidInput(cName)) {
            Utils.showError(requireContext(), getString(R.string.error_category_name))
            return false
        }

        val categories = SharedRepository.getInstance(requireContext()).getAllCategories()
        if (Category(cName).existsIn(categories)) {
            Utils.showError(requireContext(), getString(R.string.error_category_exists))
            return false
        }

        return true
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment
        )
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
