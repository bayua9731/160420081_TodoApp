package com.anmp.adv160420081week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anmp.adv160420081week4.R
import com.anmp.adv160420081week4.modelview.DetailViewModel
import com.anmp.adv160420081week4.modelview.ListViewModel
import com.anmp.adv160420081week4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null){
            val studentId=StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentId)

            observeViewModel()
        }

    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var imageview= view?.findViewById<ImageView>(R.id.imageView2)
            var progressBar=view?.findViewById<ProgressBar>(R.id.progressBar2)
            progressBar?.let { it1 -> imageview?.loadImage(it.photoUrl.toString(), it1) }
            txtID.setText(it.id.toString())
            txtStudentName.setText(it.name.toString())
            txtBod.setText(it.bod.toString())
            txtPhone.setText(it.phone.toString())
        })
    }

}