package com.afauzi.cleanarchitecturemvvm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.cleanarchitecturemvvm.databinding.ItemPostsBinding
import com.afauzi.cleanarchitecturemvvm.domain.entities.Post

class PostAdapter(
    private val posts: ArrayList<Post>
): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPostsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(posts[position]) {
                binding.tvItemId.text = id.toString()
                binding.tvItemTitle.text = title
                binding.tvItemBody.text = body
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Post>){
        posts.clear()
        posts.addAll(data)
        notifyDataSetChanged()
    }
}