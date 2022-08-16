package com.himanskdevstuff.gnv_android_sdk.presentation.adapters

import android.widget.TextView
import com.himanskdevstuff.gnv_android_sdk.R
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import de.hdodenhof.circleimageview.CircleImageView

class TopCollectionsAdapter(val listener : (View,ArrayList<NftItem?>?,Int) -> Unit)
    : PagingDataAdapter<NftItem,TopCollectionsAdapter.VideoViewHolder>(DataDifferntiator){
    val nftItemList = arrayListOf<NftItem?>()
    inner class VideoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cnt = itemView.findViewById<TextView>(R.id.tv_cnt)
        val title = itemView.findViewById<AppCompatTextView>(R.id.tv_item_name)
        val dp = itemView.findViewById<CircleImageView>(R.id.ci_tc)
        val price = itemView.findViewById<AppCompatTextView>(R.id.tv_floor_price_val)
        val itemTopCollection = itemView.findViewById<CardView>(R.id.cv_top_collection)

        init {
            itemTopCollection.setOnClickListener{

            }
        }

    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_top_collection,parent,false)
        )
    }
    object DataDifferntiator : DiffUtil.ItemCallback<NftItem>() {

        override fun areItemsTheSame(oldItem: NftItem, newItem: NftItem): Boolean {
            return oldItem.nftId == newItem.nftId
        }

        override fun areContentsTheSame(oldItem: NftItem, newItem: NftItem): Boolean {
            return oldItem == newItem
        }
    }
}