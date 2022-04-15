package com.example.t1000_ceva.ui.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t1000_ceva.R
import com.example.t1000_ceva.model.ImageTitleItem

class ImageTitleAdapter(
    private val context: Activity,
    private val itemList: List<ImageTitleItem>,
    private val layoutRes: Int = R.layout.login_type_list_item,
    private val onClick: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<ImageTitleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageTitleViewHolder {
        val view = context.layoutInflater.inflate(layoutRes, parent, false)
        return ImageTitleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ImageTitleViewHolder, position: Int) {
        val item = itemList[position]
        holder.populateView(item, position)
        holder.itemView.setOnClickListener {
            onClick?.invoke(position)
        }
    }

}


class ImageTitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var titleView: TextView = view.findViewById(R.id.titleView)
    //var imageView: ImageView = view.findViewById(R.id.imageView)

    fun populateView(item: ImageTitleItem, position: Int) {
        //imageView.setImageDrawable(item.image)
        titleView.text = "${position + 1}. " + item.title
    }
}
