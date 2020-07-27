package com.humayoun.thecollector.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.humayoun.thecollector.R
import com.humayoun.thecollector.Utils.Utils


class ItemDetailsFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavBar()
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        setHasOptionsMenu(true)
        Utils.setActionBar(requireActivity(), getString(R.string.item_details),true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}