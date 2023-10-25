package com.example.notes

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NoteDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val noteDetailTitle: TextView?
        get() = view?.findViewById(R.id.note_detail_title)
    private val noteDetail: TextView?
        get() = view?.findViewById(R.id.note_detail)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        private const val ARG_NOTE = "arg_note"

        fun newInstance(note: Note): NoteDetailsFragment {
            val fragment = NoteDetailsFragment()
            val args = Bundle()
            //args.putParcelable(ARG_NOTE, note)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_details, container, false)

        // Obtén el objeto Parcelable de tipo 'Note' en lugar de 'ContactsContract.CommonDataKinds.Note'
        //val note = arguments?.getParcelable<Note>(ARG_NOTE)

        // A continuación, puedes acceder a las propiedades de 'note'
        //noteDetailTitle?.text = note?.title
        //noteDetail?.text = note?.content

        return view
    }



}