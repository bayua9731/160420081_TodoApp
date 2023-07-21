package com.anmp.adv160420081week4.view

import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_student_detail.view.*

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View){
        val action= StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}
interface ButtonUpdateClickListener{
    fun onButtonUpdateClick(v: View){
        v.txtNotifications.text="Berhasil Update"
    }
}

