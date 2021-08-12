package co.com.ceiba.mobile.pruebadeingreso.users

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters.OnCardClickListener
import co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.DaggerUsersComponent
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.UsersModule
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UiModel
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnCardClickListener {
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private lateinit var _userViewModel: UserViewModel

    private lateinit var _adapter: UserAdapter

    private lateinit var _binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUsersComponent.builder().usersModule(UsersModule(this))
            .build().inject(this)

        initComponents()
        updateUi()
        setViewAction()
    }

    override fun onStart() {
        super.onStart()
        _userViewModel.getUserList()
    }

    private fun initComponents() {
        _binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binder.root)

        _adapter = UserAdapter(mutableListOf(), this)

        _userViewModel = ViewModelProvider(this, userViewModelFactory).get()
    }

    private fun updateUi() {
        _userViewModel.model.observe(this) {
            when (it) {
                /* is UiModel.Loading -> showDialogLoading(it.visible)
                is UiModel.Message -> showMessage(it.message)*/
                is UiModel.EmptyList -> showEmptyList(it.visible)
                is UiModel.UserList -> {
                    _adapter = UserAdapter(it.list.toMutableList(), this)
                    _binder.recyclerViewSearchResults.apply {
                        layoutManager = GridLayoutManager(context, 1)
                        adapter = _adapter
                    }
                }
            }
        }
    }

    private fun setViewAction() {
        _binder.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do Nothing
            }

            override fun afterTextChanged(s: Editable?) {
                _adapter.filter(s.toString())
            }
        })
    }

    private fun showEmptyList(visible: Boolean) {
        if (!visible) {
            _binder.recyclerViewSearchResults.visibility = View.VISIBLE
            _binder.emptyView.root.visibility = View.GONE
        } else {
            _binder.recyclerViewSearchResults.visibility = View.GONE
            _binder.emptyView.root.visibility = View.VISIBLE
        }
    }

    override fun onClick(item: UserListResponse) {
        TODO("Not yet implemented")
    }
}