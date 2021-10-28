package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.R
import com.ubaya.todoapp.view.EditTodoFragmentArgs
import com.ubaya.todoapp.viewModel.DetailTodoViewModel
import com.ubaya.todoapp.databinding.FragmentEditTodoBinding
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(),RadioClick,TodoSaveChangesClick {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }
    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
        dataBinding.radioListener=this
        dataBinding.listener=this
        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"
//        btnAdd.setOnClickListener {
//            val radio =
//                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Snackbar.make(view, "Todo Updated!", Snackbar.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
//        }

    }
    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Snackbar.make(v, "Todo Updated!", Snackbar.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }
    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            if(it.priority == 3){
//                radioHigh.isChecked = true
//            }else if(it.priority == 2){
//                radioMedium.isChecked = true
//            }else {
//                radioLow.isChecked = true
//            }
        })
    }

}