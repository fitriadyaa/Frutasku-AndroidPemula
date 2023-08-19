package id.fitriadyaa.frutasku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFruitAdapter(private val listFruit: ArrayList<FruitDatas>)  : RecyclerView.Adapter<ListFruitAdapter.CardViewViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FruitDatas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val fruits = listFruit[position]
     /*   holder.imgPhoto.setImageResource(photo)*/
        Glide.with(holder.itemView.context)
            .load(fruits.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = fruits.name
        holder.tvDetail.text = fruits.description
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listFruit[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listFruit.size
    }
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_description)
    }

}