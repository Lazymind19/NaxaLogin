package com.lazymindapps.naxamobile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazymindapps.naxamobile.R
import com.lazymindapps.naxamobile.adapter.UserListAdapter
import com.lazymindapps.naxamobile.databinding.FragmentUserPageBinding
import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.viewmodel.NaxaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class UserPageFragment : Fragment(),CoroutineScope, SearchView.OnQueryTextListener {
    lateinit var binding: FragmentUserPageBinding
    lateinit var viewModel: NaxaViewModel
    var userList: List<UserModel> = mutableListOf()
    var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserPageBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as ProfileActivity).viewModel
        fetchUsersFromDB()

        binding.searchView.isSubmitButtonEnabled = true
        binding.searchView.setOnQueryTextListener(this)

    }

    private fun searchUserFromDb(searchQuery: String) {
        userList.isEmpty()
        val search = "%$searchQuery%"
        viewModel.searchFromDatabase(search).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                userList = it
                Log.d("searched user",userList.toString())
                showUserInRecyclerView(userList)
            }
        })

    }

    private fun fetchUsersFromDB() {
        viewModel.getAllUserFromDb().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                userList = it
                showUserInRecyclerView(userList)
            }
        })
    }

    private fun showUserInRecyclerView(list: List<UserModel>) {
        val layoutManager = LinearLayoutManager(context)
        binding.rvUserList.layoutManager = layoutManager
        val adapter = UserListAdapter(list)
        binding.rvUserList.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchUserFromDb(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchUserFromDb(newText)
        }
        return true

    }
}