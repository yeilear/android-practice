package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.domain.entity.UserMessageResponse
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserPostsListUseCase
import kotlinx.coroutines.*

class UserViewModel(private val _getUserListUseCase: GetUserListUseCase,
                    private val _getUserPostsListUseCase: GetUserPostsListUseCase,
                    private val _dispatcher: CoroutineDispatcher) :  ViewModel() {

      private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get(){
            if (_model.value == null) _model.value = UiModel.Loading(true)
            return _model
        }

    private var _job: Job? = null

    fun getUserList(){
        _job?.cancel()
        _job = viewModelScope.launch(_dispatcher) {
            when (val result = _getUserListUseCase()) {

                is Success -> withContext(Dispatchers.Main) {
                    _model.value = UiModel.EmptyList(false)
                    _model.value = UiModel.UserList(result.value)
                }
                is Failure -> withContext(Dispatchers.Main) {
                    _model.value = UiModel.Message(result.reason.message.takeIf { it != null }
                        ?: UserMessageResponse.SYSTEM_ERROR.message)
                    _model.value = UiModel.EmptyList(true)
                }
            }

            withContext(Dispatchers.Main){
                _model.value = UiModel.Loading(false)
            }

            _job?.cancel()

        }
    }

    fun getPostListById(id: Int){
        _job?.cancel()
        _job = viewModelScope.launch(_dispatcher) {
            when (val result = _getUserPostsListUseCase(id)) {

                is Success -> withContext(Dispatchers.Main) {
                    _model.value = UiModel.UserPostList(result.value)
                }
                is Failure -> withContext(Dispatchers.Main) {
                    _model.value = UiModel.Message(result.reason.message.takeIf { it != null }
                        ?: UserMessageResponse.SYSTEM_ERROR.message)
                }
            }

            withContext(Dispatchers.Main){
                _model.value = UiModel.Loading(false)
            }

            _job?.cancel()
        }
    }


    override fun onCleared(){
       super.onCleared()
       _job?.cancel()
    }
}