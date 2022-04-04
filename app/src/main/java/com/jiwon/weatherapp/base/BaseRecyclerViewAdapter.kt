package com.jiwon.weatherapp.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, H : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItemList: List<T>? = null
    private var mOnItemClickListener: OnItemClickListener? = null

    protected abstract fun onBindView(holder: H, position: Int)

    override fun getItemCount(): Int {
        return mItemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.run {
                onItemClick(holder.itemView, position, this@BaseRecyclerViewAdapter)
            }
        }
        @Suppress("UNCHECKED_CAST")
        onBindView(holder as H, position)
    }

    fun getItem(position: Int): T? {
        return mItemList?.get(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<T>?) {
        mItemList = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, adapter: BaseRecyclerViewAdapter<*, *>)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }
}