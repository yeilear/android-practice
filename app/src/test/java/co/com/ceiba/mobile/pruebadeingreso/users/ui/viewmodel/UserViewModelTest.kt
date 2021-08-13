package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.com.ceiba.mobile.pruebadeingreso.MainCoroutineRule
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.domain.entity.UserMessageResponse
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserPostsListUseCase
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class UserViewModelTest : TestCase(){
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var _getUserListUseCase: GetUserListUseCase

    @Mock
    private lateinit var _getUserPostsListUseCase: GetUserPostsListUseCase

    @Mock
    private lateinit var _observer: Observer<UiModel>

    private lateinit var _userViewModel: UserViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        _userViewModel = UserViewModel(_getUserListUseCase,_getUserPostsListUseCase, Dispatchers.Main)
    }

    @Test
    fun `when getUserList use case return success then show user on recycler`() {

        runBlocking {
            val expect = UiModel.UserList(mutableListOf())
            val expectList = UiModel.EmptyList(false)

            Mockito.`when`(_getUserListUseCase.invoke())
                .thenReturn(Success(mutableListOf()))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getUserList()

            Mockito.verify(_observer).onChanged(expectList)
            Mockito.verify(_observer).onChanged(expect)

        }
    }

    @Test
    fun `when getUserList use case return failure then show message`() {

        runBlocking {

            val expect = UiModel.EmptyList(true)
            val expectMessage = UiModel.Message(UserMessageResponse.SYSTEM_ERROR.message)

            Mockito.`when`(_getUserListUseCase.invoke())
                .thenReturn(Failure(ErrorResponse(null)))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getUserList()

            Mockito.verify(_observer).onChanged(expect)
            Mockito.verify(_observer).onChanged(expectMessage)
        }
    }

    @Test
    fun `when getUserList use case return failure not null then show message`() {

        runBlocking {
            val expect = UiModel.EmptyList(true)
            val expectMessage = UiModel.Message("Error test")

            Mockito.`when`(_getUserListUseCase.invoke())
                .thenReturn(Failure(ErrorResponse("Error test")))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getUserList()

            Mockito.verify(_observer).onChanged(expect)
            Mockito.verify(_observer).onChanged(expectMessage)
        }
    }

   @Test
    fun `when getPostListById use case return success then show user on recycler`() {

        runBlocking {
            val id = 1
            val expect = UiModel.UserPostList(mutableListOf())

            Mockito.`when`(_getUserPostsListUseCase.invoke(id))
                .thenReturn(Success(mutableListOf()))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getPostListById(id)

            Mockito.verify(_observer).onChanged(expect)

        }
    }

    @Test
    fun `when getPostListById use case return failure then show message`() {

        runBlocking {
            val id = 1
            val expectMessage = UiModel.Message(UserMessageResponse.SYSTEM_ERROR.message)

            Mockito.`when`(_getUserPostsListUseCase.invoke(id))
                .thenReturn(Failure(ErrorResponse(null)))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getPostListById(id)

            Mockito.verify(_observer).onChanged(expectMessage)
        }
    }

    @Test
    fun `when getPostListById use case return failure not null then show message`() {

        runBlocking {
            val id = 1
            val expectMessage = UiModel.Message("Error test")

            Mockito.`when`(_getUserPostsListUseCase.invoke(id))
                .thenReturn(Failure(ErrorResponse("Error test")))

            _userViewModel.model.observeForever(_observer)

            _userViewModel.getPostListById(id)

            Mockito.verify(_observer).onChanged(expectMessage)
        }
    }
}