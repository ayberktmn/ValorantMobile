package com.ayberk.valorantapp

import android.app.AlertDialog
import androidx.fragment.app.Fragment


class LoadingDialog(val myfragment:Fragment) {
    private lateinit var isdialog:AlertDialog

    fun startLoading(){

        val infalter = myfragment.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)

        val bulider = AlertDialog.Builder(myfragment.requireContext())
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}