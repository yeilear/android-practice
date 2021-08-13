package co.com.ceiba.mobile.pruebadeingreso.users

import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class UserTestFactory {

    companion object {
        fun createSuccessfulResponse(code: Int): Response<List<UserEntity>> =
            Response.success(code, createResponseUsers())

        fun createResponseUsers(): List<UserEntity> =
            mutableListOf(
                UserEntity(
                    1,
                "Paolo",
                "paolo@email.com",
                    "300222222"
                )
            )

        fun createSuccessResponseRequestNullBody(code: Int): Response<List<UserEntity>> {
            val response: List<UserEntity>? = null
            return Response.success(code,response)
        }

        fun createFailureResponseRequestUsers(code: Int): Response<List<UserEntity>> {
            val response: ResponseBody = "Error 400".toResponseBody(null)
            return Response.error(code, response)
        }

        fun createSuccessfulResponsePost(code: Int): Response<List<UserPostResponse>> =
            Response.success(code, createResponsePosts())

        fun createResponsePosts(): List<UserPostResponse> =
            mutableListOf(
                UserPostResponse(
                    1,
                    "Title",
                    "This is a body"
                )
            )

        fun createSuccessResponseRequestPostsNullBody(code: Int): Response<List<UserPostResponse>> {
            val response: List<UserPostResponse>? = null
            return Response.success(code,response)
        }

        fun createFailureResponseRequestPosts(code: Int): Response<List<UserPostResponse>> {
            val response: ResponseBody = "Error 400".toResponseBody(null)
            return Response.error(code, response)
        }

        fun createUserEntity(): List<UserEntity> =
            mutableListOf(
                UserEntity(
                    1,
                    "Paolo",
                    "paolo@email.com",
                    "300222222"
                )
            )
        /*
        fun createCharacterItemResponse(): CharacterItem =
            CharacterItem("1","Name","","","Empty","yes",
                PictureInfo("Http image","jpg"),"Marvel",
                Comics(1,"Marvel", mutableListOf(ItemsComics("path","Comics")),1),
                Series(1,"Marvel", mutableListOf(ItemsSeries("path","Series")),1),
                Stories(1,"Marvel", mutableListOf(ItemStories("path","Stories","type 3")),1),
                Events(1,"Marvel", mutableListOf(ItemsEvents("parth","Events")),1),
                mutableListOf(Urls("public","http:"))
        )

        fun createCharacterInfoResult(): CharacterInfo =
            CharacterInfo( "Http image.jpg","Name","Empty",
                arrayListOf("Comics"), arrayListOf("Series"),arrayListOf("Stories"),arrayListOf("Events"),arrayListOf("http:")
            )

        fun createCharacterCard(): MutableList<CharacterCard> =
            mutableListOf(CharacterCard("1","Name","Http image.jpg"))

        fun createResponseSearchEmptyList(code: Int = 200): ResponseSearch =
            ResponseSearch(code, "Ok","","","","",
                Data(0, 20,1024,20,
                    mutableListOf()
                )
            )*/
    }
}