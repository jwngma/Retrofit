package com.store.retrofit.Interface;

import com.store.retrofit.Model.Comment;
import com.store.retrofit.Model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {


    //Simply gettibg the post
    // posts is the relative url, the relative url will be placed in the other part
    @GET("posts")
    Call<Post> getPost();


    /*sil]mply gettomg the commet*/
    @GET("posts/1/comments")
    Call<List<Comment>> getComments();


    /* getting the coment iwth post id*/
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    /* Using Retrofit Query to get only the user with id num*/
    @GET("posts")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<Post>> getPost(@Query("userId") Integer[] userId,
                             @Query("_sort") String sort,
                             @Query("_order") String order
    );

    @GET("posts")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<Post>> getPost(@Query("userId") Integer[] userId
    );

    @GET("posts")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<Post>> getPost(@Query("title") String[] title
    );



    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String, String> parameters);
    Call<List<Post>>getPost(@Url String url);

    /* creating post and uploading to the server*/

    @POST("posts")
    Call<Post> CreatePost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> CreatePost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String body);


    @FormUrlEncoded
    @POST("posts")
    Call<Post> CreatePost(@FieldMap Map<String,String> fieldMap);


    /* put , it upload the file and replace the existing file, or it create a new file , if file does not exist*/
    @PUT("posts/{id}/")
    Call<Post> putPost(@Path("id")int id,@Body Post post);


    /*patch update the existing file*/
    @PATCH("posts/{id}")
    Call<Post>updatePost(@Path("id") int id,@Body Post post);

    /* delete the post*/

    @DELETE("posts/{id}")
    Call<Void>deletePost(@Path( "id") int id);

}
