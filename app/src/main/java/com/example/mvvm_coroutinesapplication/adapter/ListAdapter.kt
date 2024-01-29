package com.example.mvvm_coroutinesapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.R

class ListAdapter(private val context: Context, private var postList:List<Post>) : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false))
    }

    override fun getItemCount(): Int = postList.size


    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val post = postList[position]
        holder.id.text = post.id.toString()
        holder.title.text = post.title
        holder.body.text = post.body
        Log.e("\n\nID>>", post.id.toString()+"\nTitle:"+post.title+"\nBody:"+post.body)
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id_textView)
        val title: TextView = itemView.findViewById(R.id.id_title_textView)
        val body: TextView = itemView.findViewById(R.id.id_description_textView)
    }

    fun setData(post: List<Post>){
        this.postList = post
        notifyDataSetChanged()
    }

}