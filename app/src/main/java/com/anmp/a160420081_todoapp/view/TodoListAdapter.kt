package com.anmp.a160420081_todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anmp.a160420081_todoapp.R
import com.anmp.a160420081_todoapp.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)

    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        var checktask = holder.view.findViewById<CheckBox>(R.id.checkBox)
        var imgEdit=holder.view.findViewById<ImageButton>(R.id.imageButton)
        checktask.text = todoList[position].title
        imgEdit.setOnClickListener {
            val action =TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true) {
                adapterOnClick(todoList[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return todoList.size

    }
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}
