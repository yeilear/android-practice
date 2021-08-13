package co.com.ceiba.mobile.pruebadeingreso.users.data.repository

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.common.network.NetworkManagerState
import co.com.ceiba.mobile.pruebadeingreso.users.UserTestFactory
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.RoomUserLocal
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.UserApi
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryUserImplTest : TestCase(){
    @Mock
    private lateinit var _local: RoomUserLocal

    @Mock
    private lateinit var _remote: UserApi

    @Mock
    private lateinit var _networkManagerState: NetworkManagerState


    private lateinit var _repositoryUserImpl: RepositoryUserImpl

    @Before
    fun setUpInject() {
        _repositoryUserImpl = RepositoryUserImpl(_local, _remote, _networkManagerState)
    }

    @Test
    fun `when getUsersList and it's success and save response`() {

        runBlocking {
            val expect = true
            val successResult = Success(UserTestFactory.createResponseUsers())

            Mockito.`when`(_local.readAllUsers()).thenReturn(mutableListOf())
            Mockito.`when`(_networkManagerState.isConnected()).thenReturn(true)

            Mockito.`when`(_remote.getUsersList()).thenReturn(successResult)

            when (val result = _repositoryUserImpl.getUsersList()) {

                is Success -> assertEquals(expect, result.value.isNotEmpty())
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUsersList and it's success with users local`() {

        runBlocking {
            val expect = true

            Mockito.`when`(_local.readAllUsers()).thenReturn(UserTestFactory.createResponseUsers())

            when (val result = _repositoryUserImpl.getUsersList()) {

                is Success -> assertEquals(expect, result.value.isNotEmpty())
                else -> assert(false)
            }
        }
    }


    @Test
    fun `when getUsersList but haven't connection and return Failure`() {

        runBlocking {
            Mockito.`when`(_local.readAllUsers()).thenReturn(mutableListOf())
            Mockito.`when`(_networkManagerState.isConnected()).thenReturn(false)

            when (val result = _repositoryUserImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUserPost and it's success`() {

        runBlocking {
            val id = 1
            val expect = true
            val successResult = Success(UserTestFactory.createResponsePosts())

            Mockito.`when`(_networkManagerState.isConnected()).thenReturn(true)

            Mockito.`when`(_remote.getUserPost(id)).thenReturn(successResult)

            when (val result = _repositoryUserImpl.getUserPost(id)) {

                is Success -> assertEquals(expect, result.value.isNotEmpty())
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUserPost but haven't connection and return Failure`() {

        runBlocking {
            val id = 1
            Mockito.`when`(_networkManagerState.isConnected()).thenReturn(false)

            when (val result = _repositoryUserImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }
}