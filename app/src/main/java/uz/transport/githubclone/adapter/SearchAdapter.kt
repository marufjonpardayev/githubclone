package uz.transport.githubclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.davlatov.githubproject.data.model.search.SearchRepository
import uz.transport.githubclone.databinding.ItemSearchResultBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.VH>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class VH(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                val details = dif.currentList[adapterPosition]
                //Glide.get().load(details.items!![adapterPosition-1].avatar_url).into(ivProfile)
                tvProfileName.text = details.items!![adapterPosition-1].login
                tvNameRepos.text = details.items[adapterPosition-1].name
                tvDescRepos.text = details.items[adapterPosition-1].description.toString()
                tvCountStar.text = details.items[adapterPosition-1].stargazers_count.toString()
                tvLanguage.text = details.items[adapterPosition-1].language

            }
        }
    }

    fun submitList(list: List<SearchRepository>) {
        dif.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<SearchRepository>() {
            override fun areItemsTheSame(
                oldItem: SearchRepository,
                newItem: SearchRepository,
            ): Boolean = false

            override fun areContentsTheSame(
                oldItem: SearchRepository,
                newItem: SearchRepository,
            ): Boolean =
                oldItem == newItem
        }
    }
}