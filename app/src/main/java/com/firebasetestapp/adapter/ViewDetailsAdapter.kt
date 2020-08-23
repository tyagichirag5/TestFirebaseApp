package com.firebasetestapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebasetestapp.activities.BaseActivity
import com.firebasetestapp.databinding.AdapterViewDetailsBinding
import com.firebasetestapp.model_classes.UserData

class ViewDetailsAdapter(val baseActivity: BaseActivity, val arrayList: ArrayList<UserData>) :
    RecyclerView.Adapter<ViewDetailsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: AdapterViewDetailsBinding =
            AdapterViewDetailsBinding.inflate(baseActivity.layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = getItem(position)//Retrieve values from array list
        holder.onBind(model)//Pass on values to xml through data binding
    }

    private fun getItem(position: Int): UserData {
        return arrayList[position]
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(var binding: AdapterViewDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: UserData) {
            binding.apply {
                dataModel = model
            }
        }

    }
}