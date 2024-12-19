package com.dicoding.mistoriyy.storiyy


import android.app.Activity
import android.content.Intent
import android.view.*
import androidx.core.util.Pair
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.dicoding.mistoriyy.Detail.DetailActivity
import com.dicoding.mistoriyy.databinding.ItemstoriyBinding

class StoriyAdapter : ListAdapter<ListStoriyItem, StoriyAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemstoriyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListStoriyItem) {
            binding.tvItemName.text = story.name
            Glide.with(binding.root)
                .load(story.photoUrl)
                .into(binding.ivItemPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemstoriyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)

        holder.itemView.setOnClickListener {
            val storyId = story.id
            val intentId = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.STORY_ID, storyId)
            }

            val optionCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.ivItemPhoto, "imageStoryDetail"),
                    Pair(holder.binding.tvItemName, "nameStoryDetail")
                )

            holder.itemView.context.startActivity(intentId, optionCompat.toBundle())
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ListStoriyItem> =
            object : DiffUtil.ItemCallback<ListStoriyItem>() {
                override fun areItemsTheSame(
                    oldItem: ListStoriyItem,
                    newItem: ListStoriyItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ListStoriyItem,
                    newItem: ListStoriyItem
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}