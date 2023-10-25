package com.example.notes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateNoteFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val noteInput: EditText?
        get() = view?.findViewById(R.id.note_input)

    private val noteTitleInput: EditText?
        get() = view?.findViewById(R.id.note_title_input)

    private val recyclerView: RecyclerView?
        get() = view?.findViewById(R.id.recyclerView)

    private val saveButton: Button?
        get() = view?.findViewById(R.id.save_button)

    private val notesList: MutableList<Note> by lazy {
        (requireActivity() as MainActivity).notesList
    }




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





        val adapter = CustomAdapter(notesList)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter

        saveButton?.setOnClickListener {
            val note = noteInput?.text?.toString()?.trim() ?: ""
            val noteTitle = noteTitleInput?.text?.toString()?.trim() ?: ""

            if (note.isEmpty() && noteTitle.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.note_text_validation), Toast.LENGTH_LONG).show()
            } else if (noteTitle.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.note_title_text_validation), Toast.LENGTH_LONG).show()
            } else if(note.isNotEmpty() && noteTitle.isNotEmpty()) {
                // Crea una nueva nota
                val newNote = Note(noteTitle, note)
                notesList.add(newNote)
                adapter.notifyDataSetChanged()


                Log.d("NoteDetails", "Title: ${newNote.title}")
                Log.d("NoteDetails", "Content: ${newNote.content}")


                hideKeyboard()
                clearInputFields()
            }
        }


    }


    private fun clearInputFields() {
        noteInput?.text?.clear()
        noteTitleInput?.text?.clear()

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