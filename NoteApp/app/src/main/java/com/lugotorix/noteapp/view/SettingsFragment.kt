package com.lugotorix.noteapp.view

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lugotorix.noteapp.R
import com.lugotorix.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allDelete_btn.setOnClickListener {
            deleteAllNotes()
        }

        /*
        val appSettingPrefs: SharedPreferences = requireActivity().getSharedPreferences("AppSettingPrefs",  0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn : Boolean = appSettingPrefs.getBoolean("NightMode", false)

        if (isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }



        switch1.setOnCheckedChangeListener { _, b ->
            if (b){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NighMode", true)
                sharedPrefsEdit.apply()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NighMode", false)
                sharedPrefsEdit.apply()
            }

        }
        */

    }

    private fun deleteAllNotes(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet"){ _, _ ->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(), "Başarıyla Silindi!", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Hayır"){ _, _ ->

        }
        builder.setTitle("Tüm Notları Sil!")
        builder.setMessage("Tüm Silmek İstediğine Emin Misin?")
        builder.create().show()
    }

}