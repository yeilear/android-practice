package co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

class UserPostAdapter (private var _post: MutableList<UserPostResponse>) :
    RecyclerView.Adapter<UserPostAdapter.ViewHolder>() {

    private lateinit var _mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _mContext = parent.context

        val view = LayoutInflater.from(_mContext).inflate(R.layout.post_list_item, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = _post[position]

        with(holder){
            binding.title.text = post.title
            binding.body.text = post.body
        }
    }

    override fun getItemCount(): Int = _post.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = PostListItemBinding.bind(view)
    }
}