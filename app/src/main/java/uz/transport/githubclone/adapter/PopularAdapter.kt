package uz.transport.githubclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.transport.githubclone.R
import uz.transport.githubclone.model.Popular

class PopularAdapter(var items:ArrayList<Popular>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.repository_popula_layout,parent,false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=items[position]
        if (holder is PopularViewHolder){
            val repName=holder.repName
            item.repName= repName.toString()

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val repName: TextView =view.findViewById(R.id.repName)

}
