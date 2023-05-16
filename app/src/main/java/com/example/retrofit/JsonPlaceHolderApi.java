package com.example.retrofit;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
   // @GET("posts")
   // Call<List<Post>> getPosts();
  //  @GET("posts/2/comments")
  //  Call<List<Comment>> getComments();
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id")int postId);
   // @GET("posts")
   // Call<List<Post>> getPosts(@Query("userId") int userId);
    @GET("posts")
     Call<List<Post>> getPosts(
  //  @Query("userId") int userId,
    //@Query("_sort") String sort,
   // @Query("_order")String order
  @Query("userId") Integer userId,
  @Query("userId") Integer userId2,
  @Query("_sort") String sort,
  @Query("_order")String order
    );
    @POST("posts")
    Call<Post>createPost(@Body Post post);

}
