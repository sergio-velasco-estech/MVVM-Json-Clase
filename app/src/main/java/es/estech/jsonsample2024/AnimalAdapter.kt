package es.estech.jsonsample2024

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.estech.jsonsample2024.databinding.VistaCeldaBinding

class AnimalAdapter(
    private val list: ArrayList<Animal>,
    private val listener: MyClick
) : Adapter<AnimalAdapter.MyHolder>() {

    interface MyClick {
        fun onHolderClick(animal: Animal)
    }

    inner class MyHolder(val binding: VistaCeldaBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(VistaCeldaBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val animal = list[position]

        holder.binding.tvName.text = animal.name
        Glide.with(holder.itemView).load(animal.imageLink).into(holder.binding.ivImage)

        holder.itemView.setOnClickListener {
            listener.onHolderClick(animal)
        }
    }
}