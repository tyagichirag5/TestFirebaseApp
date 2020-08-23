package com.firebasetestapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebasetestapp.R
import com.firebasetestapp.activities.MainActivity
import com.firebasetestapp.adapter.ViewDetailsAdapter
import com.firebasetestapp.databinding.FragmentViewDetailsBinding
import com.firebasetestapp.model_classes.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ViewDetailsFragment : BaseFragment() {
    lateinit var binding: FragmentViewDetailsBinding
    private lateinit var database: DatabaseReference
    var arrayList: ArrayList<UserData>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.view_details), true)
        binding = FragmentViewDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        arrayList?.clear()
        database = Firebase.database.reference.child("users")

        getAllData()
    }

    private fun getAllData() {
        baseActivity!!.startProgressDialog()

        val postListener = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                baseActivity!!.stopProgressDialog()
                arrayList = ArrayList()
                if (snapshot.exists()) {
                    binding.errorTV.visibility = View.GONE
                    binding.headingLL.visibility = View.VISIBLE
                    for (postSnapShot in snapshot.children) {
                        val data: UserData? =
                            postSnapShot.getValue(UserData::class.java)
                        arrayList!!.add(data!!)
                    }
                    setAdapter()
                } else {
                    binding.errorTV.visibility = View.VISIBLE
                    binding.headingLL.visibility = View.GONE
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                baseActivity!!.stopProgressDialog()
                Log.e("", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)

    }

    private fun setAdapter() {
        val adapter = ViewDetailsAdapter(baseActivity!!, arrayList!!)
        binding.listRV.adapter = adapter
    }
}