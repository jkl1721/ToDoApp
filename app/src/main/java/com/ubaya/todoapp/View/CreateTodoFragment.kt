package com.ubaya.todoapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ubaya.todoapp.Model.Todo
import com.ubaya.todoapp.R
import com.ubaya.todoapp.ViewModel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class CreateTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    private lateinit var viewModel: DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        btnAdd.setOnClickListener {
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString())
            val list = listOf(todo)
            viewModel.addTodo(list)
            Snackbar.make(view, "Todo Added!", Snackbar.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}