package com.example.simpleappmvvm.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleappmvvm.R
import com.example.simpleappmvvm.data.Note
import com.example.simpleappmvvm.databinding.CustomlistlayoutBinding
import com.example.simpleappmvvm.generated.callback.OnClickListener


// pass the data class as parameter
class Recycleviewadaptor(val list_note: List<Note>,

                            val clickListener: (Note) -> Unit ) : RecyclerView.Adapter<ViewHolder>() {


    // create the list item using xml layout temple(customlistlayout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)
        val binding : CustomlistlayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.customlistlayout,parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list_note[position] , clickListener)
    }



    // return the number of element encounter from the dataset
    override fun getItemCount(): Int {

        return list_note.size

    }

}





class ViewHolder (val binding : CustomlistlayoutBinding  ) : RecyclerView.ViewHolder(binding.root){
    // view holder connect the text view

    // connect the data class and replace each value
    fun bind(note: Note ,  clickListener: (Note) -> Unit){
        binding.TitleTextview.text = note.title
        binding.DescriptionTextview.text = note.description
        binding.priorityTextview.text = note.priority
        binding.customlistnote.setOnClickListener{clickListener(note)}
    }


}