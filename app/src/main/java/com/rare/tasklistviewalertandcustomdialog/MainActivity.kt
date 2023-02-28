package com.rare.tasklistviewalertandcustomdialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.rare.tasklistviewalertandcustomdialog.databinding.ActivityMainBinding
import com.rare.tasklistviewalertandcustomdialog.databinding.AddDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter
    var arraylist = ArrayList<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        adapter = UserAdapter(this, arraylist)
        setContentView(binding.root)
        binding.lv.adapter = adapter
        binding.fabAdd.setOnClickListener {
            var dialogView = AddDialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogView.root)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
            dialogView.btnSave.setOnClickListener {
                if (dialogView.etName.text.toString().trim().isEmpty()) {
                    dialogView.etName.error = "Enter the Name"
                } else if (dialogView.etRollNo.text.toString().trim().isEmpty()) {
                    dialogView.etRollNo.error = "Enter your Roll Number"
                } else if (dialogView.etPhno.text.toString().trim().isEmpty()) {
                    dialogView.etPhno.error = "Enter your phone number"
                } else {
                    var usermodel = UserModel(
                        dialogView.etName.text.toString(),
                        dialogView.etRollNo.text.toString().toInt(),
                        dialogView.etPhno.text.toString().toInt(),
                    )
                    arraylist.add(usermodel)
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()

                }

            }
        }
    }
}