package com.rare.tasklistviewalertandcustomdialog

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.rare.tasklistviewalertandcustomdialog.databinding.StuListBinding

class UserAdapter(var activity: MainActivity, var arraylist : ArrayList<UserModel>) : BaseAdapter(){
    override fun getCount(): Int {
    return arraylist.size
    }

    override fun getItem(p0: Int): Any {
    return arraylist[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = StuListBinding.inflate(activity.layoutInflater)
        binding.tvName.setText(arraylist[p0].name.toString())
        binding.tvRollno.setText(arraylist[p0].rollno.toString().toInt())
        binding.tvPhno.setText(arraylist[p0].phno.toString().toInt())
        return binding.root
    }
}