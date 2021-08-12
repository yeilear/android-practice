package co.com.ceiba.mobile.pruebadeingreso.users

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.common.USER_ID
import co.com.ceiba.mobile.pruebadeingreso.common.dialog.DataBuilderDialog
import co.com.ceiba.mobile.pruebadeingreso.common.dialog.DialogBuilder
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters.UserPostAdapter
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.DaggerUsersComponent
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.UsersModule
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UiModel
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModelFactory
import javax.inject.Inject

class PostActivity : AppCompatActivity(){
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private lateinit var _userViewModel: UserViewModel

    private lateinit var _binder: ActivityPostBinding

    private lateinit var _adapter: UserPostAdapter

    private var _dialogMessage: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUsersComponent.builder().usersModule(UsersModule(this))
            .build().inject(this)

        initComponents()
        updateUi()
    }

    override fun onStart() {
        super.onStart()
        intent.getIntExtra(USER_ID,0).let {
            _userViewModel.getPostListById(it)
        }
    }

    private fun initComponents() {
        _binder = ActivityPostBinding.inflate(layoutInflater)
        setContentView(_binder.root)

        _adapter = UserPostAdapter(mutableListOf())

        _userViewModel = ViewModelProvider(this, userViewModelFactory).get()
    }

    private fun updateUi() {
        _userViewModel.model.observe(this) {
            when (it) {
                is UiModel.Message -> showMessage(it.message)
                is UiModel.UserPostList -> {
                    _adapter = UserPostAdapter(it.list.toMutableList())
                    _binder.recyclerViewPostsResults.apply {
                        layoutManager = GridLayoutManager(context, 1)
                        adapter = _adapter
                    }
                }
                else -> return@observe
            }
        }
    }

    private fun showMessage(message: String) {
        _dialogMessage?.cancel()
        _dialogMessage = DialogBuilder.showAlertDialog(this, DataBuilderDialog(message = message))
        _dialogMessage?.show()
    }


}