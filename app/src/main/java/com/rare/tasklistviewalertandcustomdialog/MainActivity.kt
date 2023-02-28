package com.rare.tasklistviewalertandcustomdialog

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import com.rare.tasklistviewalertandcustomdialog.databinding.ActivityMainBinding
import com.rare.tasklistviewalertandcustomdialog.databinding.AddDialogBinding

class MainActivity : AppCompatActivity(),Click {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter
    var arraylist = ArrayList<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        adapter = UserAdapter(this, arraylist, this)
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

    override fun update(position: Int) {
        var updatedialogView = AddDialogBinding.inflate(layoutInflater)
        var dialog = Dialog(this)
       updatedialogView.etName.setText(arraylist[position].name)
        updatedialogView.etRollNo.setText(arraylist[position].rollno.toString())
        updatedialogView.etPhno.setText(arraylist[position].phno.toString())

        dialog.setContentView(updatedialogView.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        updatedialogView.btnSave.setOnClickListener {
            if (updatedialogView.etName.text.toString().trim().isEmpty()) {
                updatedialogView.etName.error = "Enter the Name"
            } else if (updatedialogView.etRollNo.text.toString().trim().isEmpty()) {
                updatedialogView.etRollNo.error = "Enter your Roll Number"
            } else if (updatedialogView.etPhno.text.toString().trim().isEmpty()) {
                updatedialogView.etPhno.error = "Enter your phone number"
            } else {

                arraylist.set(position, UserModel(name=updatedialogView.etName.text.toString(),
                    rollno = updatedialogView.etRollNo.text.toString().toInt(),
                    phno= updatedialogView.etPhno.text.toString().toInt(),
                ))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        dialog.show()

    }

    override fun delete(position: Int) {
        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("Delete Alert!!")
        dialog.setMessage("Are you sure ")
        dialog.setPositiveButton("yes") { _, _ ->
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            arraylist.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        dialog.setNegativeButton("No") { _, _ ->
            Toast.makeText(this, "Operation Cancelled", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}