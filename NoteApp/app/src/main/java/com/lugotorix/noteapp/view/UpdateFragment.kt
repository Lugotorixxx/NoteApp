package com.lugotorix.noteapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lugotorix.noteapp.R
import com.lugotorix.noteapp.model.Note
import com.lugotorix.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment(){

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var  mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.updateTitle_et.setText(args.currentNote.title)
        view.updateTopic_et.setText(args.currentNote.topic)
        view.updateDate_txt.setText(args.currentNote.date)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val title = updateTitle_et.text.toString()
        val topic = updateTopic_et.text.toString()

        if (inputCheck(title, topic)){
            val updateNote = Note(args.currentNote.id, title, topic, args.currentNote.date)
            mNoteViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Başarıyla Güncellendi!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Tüm Boşlukları Doldurunuz!", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(title: String, topic: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(topic))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.config_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteNote()
        }else if(item.itemId == R.id.menu_update){
            updateTitle_et.isEnabled = true
            updateTopic_et.isEnabled = true
            update_btn.visibility = View.VISIBLE
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet"){ _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), "Başarıyla Silindi!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Hayır"){ _, _ ->

        }
        builder.setTitle("Notu Sil? ${args.currentNote.title}?")
        builder.setMessage("Silmek İstediğinize Emin Misiniz?")
        builder.create().show()
    }
}