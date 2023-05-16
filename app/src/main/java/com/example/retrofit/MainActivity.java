package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity<call> extends AppCompatActivity {
private TextView textViewResult;
private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
      // getPosts();
      //  getComments();
        createPost();
    }
    private void getPosts(){
        Call<List<Post>> call= jsonPlaceHolderApi.getPosts(1,4,null,null);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:" +response.code());
                    return;
                }
                List<Post> posts=response.body();
                for(Post post:posts){
                    String content="";
                    content +="ID:" +post.getId()+"\n";
                    content+="User Id:" +post.getUserId()+"\n";
                    content+="Title:" +post.getTitle() +"\n";
                    content+="Text:" +post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });

    }
    private void getComments(){
        Call<List<Comment>> call= jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:" +response.code());
                    return;
                }
                List<Comment> comments=response.body();
                for(Comment comment:comments){
                    String content="";
                    content +="ID:" +comment.getId()+"\n";
                    content +=" Post ID:" +comment.getPostId()+"\n";
                    content+="Name:" +comment.getName()+"\n";
                    content+="Email:" +comment.getEmail() +"\n";
                    content+="Text:" +comment.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }



            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
    private void createPost(){
        Post post=new Post(23,"new title","new text");
        call call=jsonPlaceHolderApi.createPost(post);
        //wants to send this the server
        ((Call<?>) call).enqueue(new Callback<Post>() {


            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:" +response.code());
                    return;
                }
                Post postResponse=response.body();
                String content="";
                content+="Code:"+response.code()+"\n"
                content +="ID:" +postResponse.getId()+"\n";
                content +=" user ID:" +postResponse.getUserId()+"\n";

                content+="Title:" +postResponse.getTitle() +"\n";
                content+="Text:" +postResponse.getText() + "\n\n";
                textViewResult.setText(content);

            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }