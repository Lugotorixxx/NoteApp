package com.lugotorix.noteapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lugotorix.noteapp.R
import com.lugotorix.noteapp.adapter.NoteAdapter
import com.lugotorix.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_update.*

class ListFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = NoteAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        //recyclerView.layoutManager = LineerLayoutManager(requireContext())

        gridLayoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)


        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings){
            findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }
*/

}