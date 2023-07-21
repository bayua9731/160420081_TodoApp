package com.anmp.adv160420081week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anmp.adv160420081week4.R
import com.anmp.adv160420081week4.databinding.FragmentStudentDetailBinding
import com.anmp.adv160420081week4.modelview.DetailViewModel
import com.anmp.adv160420081week4.modelview.ListViewModel
import com.anmp.adv160420081week4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.student_list_item.*


class StudentDetailFragment : Fragment(),ButtonUpdateClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            dataBinding.listener=this
            val studentId=StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentId)

            observeViewModel(view)
        }

    }

    fun observeViewModel(view:View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student=it
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context,"Berhasil",Toast.LENGTH_LONG)
    }
}