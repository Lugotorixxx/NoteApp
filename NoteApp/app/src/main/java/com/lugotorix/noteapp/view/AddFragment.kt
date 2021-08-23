package com.lugotorix.noteapp.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.BoringLayout
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Switch
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lugotorix.noteapp.R
import com.lugotorix.noteapp.model.Note
import com.lugotorix.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.add_btn
import java.sql.Array
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hide()
    }



    private fun insertDataToDatabase(){
        val title = addTitle_et.text.toString()
        val topic = addTopic_et.text.toString()

        val currentDate = Calendar.getInstance(Locale.getDefault())
        val year = currentDate.get(Calendar.YEAR)
        var month = currentDate.get(Calendar.MONTH)
        month += 1 //fix
        var monthName = month.toString()

        if (month==1){
            monthName = "Ocak"
        }
        else if (month==2){
            monthName = "Şubat"
        }
        else if (month==3){
            monthName = "Mart"
        }
        else if (month==4){
            monthName = "Nisan"
        }
        else if (month==5){
            monthName = "Mayıs"
        }
        else if (month==6){
            monthName = "Haziran"
        }
        else if (month==7){
            monthName = "Temmuz"
        }
        else if (month==8){
            monthName = "Ağustos"
        }
        else if (month==9){
            monthName = "Eylül"
        }
        else if (month==10){
            monthName = "Ekim"
        }
        else if (month==11){
            monthName = "Kasım"
        }
        else if (month==12){
            monthName = "Aralık"
        }


        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val dateTime = "${day}, ${monthName} ${year}"
        var date = dateTime

        if (inputCheck(title, topic)){
            val note = Note(0, title, topic, date)
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Başarıyla Eklendi!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Tüm Boşlukları Doldurunuz!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, topic: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(topic))
    }

    private fun hide(){
        scrollView.setOnTouchListener { view, motionEvent ->
            closeKeyboard()

        }
    }

    private fun closeKeyboard(): Boolean{
        val view = requireActivity().currentFocus
        if (view!=null){
            val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return true
    }





}