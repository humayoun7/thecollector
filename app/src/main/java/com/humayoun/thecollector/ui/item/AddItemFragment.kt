package com.humayoun.thecollector.ui.item

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.humayoun.thecollector.Constants
import com.humayoun.thecollector.R
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.data.item.Item
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddItemFragment : Fragment() {

    private lateinit var navController: NavController
    private var imageURI: Uri? = null
    private var categoryName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setReceivingArguments()
        setupUI()
    }

    fun setupUI() {
        setNavBar()

        button_select_image.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
        }

        button_add_item.setOnClickListener{
            val title = et_title.text.toString()
            val desc = et_description.text.toString()
            val rating = ratingBar.rating
            var imageUriStr = imageURI.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val itemDao = CollectorDatabase.getCollectorDatabase(requireActivity().applicationContext).ItemDao()
                itemDao.insertItem(
                    Item(
                        title,
                        desc,
                        rating,
                        imageUriStr,
                        categoryName ?: ""
                    )
                )
            }
            navController.navigateUp()
        }
    }

    fun setReceivingArguments() {
        categoryName = arguments?.get(Constants.CATEGORY_NAME).toString()
    }

    fun setNavBar() {
        navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment
        )
        setHasOptionsMenu(true)
        Utils.setActionBar(requireActivity(), getString(R.string.add_new_item), true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode == Activity.RESULT_OK && requestCode == 0) {
            imageURI = data?.getData()
            imageURI?.let {
                Glide.with(requireActivity())
                    .load(it)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_item)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}
