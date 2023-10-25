package com.example.notes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateNoteFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null



    private val noteInput: EditText?
        get() = view?.findViewById(R.id.note_input)

    private val noteTitleInput: EditText?
        get() = view?.findViewById(R.id.note_title_input)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val customAdapter = CustomAdapter()

        val saveButton = view.findViewById<Button>(R.id.save_button)

        saveButton?.setOnClickListener {

            val note = noteInput?.text?.toString()?.trim() ?: ""
            val noteTitle = noteTitleInput?.text?.toString()?.trim() ?: ""

            if ((note.isEmpty() && noteTitle.isEmpty()) || (note.isEmpty())) {
                Toast.makeText(requireContext(), getString(R.string.note_text_validation), Toast.LENGTH_LONG).show()
            } else if(noteTitle.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.note_title_text_validation), Toast.LENGTH_LONG).show()
            } else {


                // Agrega la nueva nota con el título capturado
                customAdapter.addNoteWithTitle(noteTitle)

                // Notifica al RecyclerView que se ha insertado un nuevo elemento
                recyclerView.adapter = customAdapter

                hideKeyboard()
                clearInputFields()

            }


        }






    }



    private fun clearInputFields() {
        noteInput?.text?.clear()
    }


    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}