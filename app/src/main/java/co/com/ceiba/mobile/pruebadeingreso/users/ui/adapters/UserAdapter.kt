package co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import java.util.*

class UserAdapter (private var _users: MutableList<UserEntity>,
                   private var _listener: OnCardClickListener,
                   private var _userSearchList: List<UserEntity> = _users.toList()) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var _mContext: Context

    fun filter(charText: String?) {
        _users.clear()
        if (charText.isNullOrEmpty())
            _users = _userSearchList.toMutableList()
        else {
            for (user: UserEntity in _userSearchList) {
                if (user.name.lowercase(Locale.getDefault()).contains(charText.lowercase(Locale.getDefault())))
                    _users.add(user)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _mContext = parent.context

        val view = LayoutInflater.from(_mContext).inflate(R.layout.user_list_item, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = _users[position]

        with(holder){
            binding.name.text =  user.name
            binding.email.text = user.email
            binding.phone.text = user.phone
            binding.btnViewPost.setOnClickListener{
                _listener.onClick(user)
            }
        }
    }

    override fun getItemCount(): Int = _users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = UserListItemBinding.bind(view)
    }
}