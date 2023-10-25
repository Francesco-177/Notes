package com.example.notes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter constructor(private  val notesList : List<Note>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val images = intArrayOf(R.drawable.baseline_sticky_note_2_24)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = notesList[position].title
        holder.itemImage.setImageResource(images[0])
        holder.cardView.setOnClickListener {
            // Aquí puedes implementar una acción cuando se haga clic en el CardView
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var cardView: CardView = itemView.findViewById(R.id.card_view) // Asigna el CardView

    }

}
