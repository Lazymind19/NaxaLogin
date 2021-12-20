package com.lazymindapps.naxamobile.adapter

import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lazymindapps.naxamobile.R
import com.lazymindapps.naxamobile.databinding.RvUserListBinding
import com.lazymindapps.naxamobile.model.UserModel
import com.squareup.picasso.Picasso

class UserListAdapter(val userList:List<UserModel>) :RecyclerView.Adapter<UserListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  RvUserListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = userList[position]
        holder.bind(users)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val itemBinding: RvUserListBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(userModel: UserModel){
            itemBinding.tvEmail.text = userModel.email
            itemBinding.tvFirstName.text = userModel.first_name
            Picasso.get().load(userModel.avatar).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(itemBinding.ivLogo)


        }

    }
}