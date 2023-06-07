package com.anmp.a160420081_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.a160420081_todoapp.R
import com.anmp.a160420081_todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var txtJudulTodo=view.findViewById<TextView>(R.id.txtJudulTodo)
        var btnAdd=view.findViewById<Button>(R.id.btnAdd)
        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            var radioGroupPriority=view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio =
                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()

        }


    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            var txtTitle= view?.findViewById<TextView>(R.id.txtTitle)
            var txtNotes=view?.findViewById<TextView>(R.id.txtNotes)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
            var radioLow=view?.findViewById<RadioButton>(R.id.radioLow)
            var radioMedium=view?.findViewById<RadioButton>(R.id.radioMedium)
            var radioHigh=view?.findViewById<RadioButton>(R.id.radioHigh)
            when (it.priority) {
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true
            }

        })
    }


}