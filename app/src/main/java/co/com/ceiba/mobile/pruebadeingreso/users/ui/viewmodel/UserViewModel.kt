package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserPostsListUseCase
import kotlinx.coroutines.*

class UserViewModel(private val _getUserListUseCase: GetUserListUseCase,
                    private val _getUserPostsListUseCase: GetUserPostsListUseCase,
                    private val _dispatcher: CoroutineDispatcher) :  ViewModel() {

      private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get(){
            if (_model.value == null) {
                UiModel.Loading(true)
                UiModel.SearchBar(false)
            }
            return _model
        }

    private var _job: Job? = null
    private var _jobSpinnerList: Job? = null

    init {

    }


    override fun onCleared(){
       super.onCleared()
       _job?.cancel()
        _jobSpinnerList?.cancel()
    }


    sealed class UiModel{
        data class Loading(val visible: Boolean) : UiModel()
        data class SearchBar(val visible: Boolean) : UiModel()
        data class Message(val message: String): UiModel()
    }
}