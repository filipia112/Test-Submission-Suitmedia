package com.example.testsubmissionsuitemedia.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testsubmissionsuitemedia.R
import com.example.testsubmissionsuitemedia.data.source.remote.network.response.DataItem
import com.example.testsubmissionsuitemedia.databinding.ItemListUsersBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    private var listData = ArrayList<DataItem>()

    fun setData(newListData: List<DataItem>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.avatar)
                    .into(IvUsers)
                val firstName = data.firstName
                val lastName = data.lastName
                tvFullNameUsers.text = itemView.context.getString(R.string.full_name_format,firstName,lastName)
                tvEmailUsers.text = data.email
            }
        }
    }
}
