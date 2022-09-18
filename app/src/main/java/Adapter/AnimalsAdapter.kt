package Adapter

import Interface.CardListener
import Model.Animals
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc.animalsdata.R
import kotlinx.android.synthetic.main.animalsitem.view.*


class AnimalsAdapter(val listDataAnimals: ArrayList<Animals>, val cardListener: CardListener):
    RecyclerView.Adapter<AnimalsAdapter.viewHolder>() {

    class viewHolder(itemView: View, val cardListener: CardListener):
        RecyclerView.ViewHolder(itemView) {
        val namahewan = itemView.namahewan
        val jenishewan = itemView.jenishewan
        val usiahewan = itemView.usia
        val animal_image = itemView.imageAnimal
//        val delete = itemView.delete_btn
        val edit = itemView.cancel_btn

        fun setData(data: Animals) {
            namahewan.text = data.nama
            jenishewan.text = data.jenis
            usiahewan.text= data.usia.toString()
            animal_image.setImageURI(Uri.parse(data.imageUri))
            if (data.imageUri.isNotEmpty()) {
                animal_image.setImageURI(Uri.parse(data.imageUri))
            }
            itemView.setOnClickListener {
                cardListener.onCardClick(adapterPosition)
            }

            edit.setOnClickListener {
                cardListener.onEditClick(adapterPosition)
            }

//            delete.setOnClickListener{
//                cardListener.onDeleteClick(adapterPosition)
//            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.animalsitem, parent, false)
        return viewHolder(view, cardListener)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){
        holder.setData(listDataAnimals[position])
    }

    override fun getItemCount(): Int{
        return listDataAnimals.size
    }




}