package co.com.ceiba.mobile.pruebadeingreso.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.DaggerUsersComponent
import co.com.ceiba.mobile.pruebadeingreso.users.ui.di.UsersModule
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private lateinit var _userViewModel: UserViewModel

    private lateinit var _binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUsersComponent.builder().usersModule(UsersModule(this))
            .build().inject(this)

        initComponents()
    }

    private fun initComponents() {
        _binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binder.root)

        _userViewModel = ViewModelProvider(this, userViewModelFactory).get()
    }
}