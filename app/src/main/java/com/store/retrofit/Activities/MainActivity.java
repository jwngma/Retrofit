package com.store.retrofit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.retrofit.Adapters.PostAdapter;
import com.store.retrofit.Interface.GitHubClient;
import com.store.retrofit.Interface.JsonPlaceHolderApi;
import com.store.retrofit.Model.Comment;

import com.store.retrofit.Model.Post;
import com.store.retrofit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView final_textview;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private Gson gson;
    //Future Studio

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: main has been created");
        final_textview = findViewById(R.id.text_view);

        /* this is reequired if we want to see the logs of the process*/
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        /* this is reequired if we want to see the logs of the process*/

        initGetpost();
        //initComments();
        //  initCreatePost();

        //initPut();
        //initPatch();
        //initDelete();

        //initHashMap();
        // initHashSet();

        //Future Studio
        // initRetrofit();


    }

    private void initRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        Retrofit retrofit = builder.build();
        GitHubClient gitHubClient = retrofit.create(GitHubClient.class);


    }

    private void initDelete() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Void> call = jsonPlaceHolderApi.deletePost(12);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText(response.code());
                    return;

                }

                final_textview.setText(response.code());


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });
    }

    private void initPatch() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Post post = new Post("344", "vthis is the totle", "real body is this");
        Call<Post> call = jsonPlaceHolderApi.updatePost(21, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    final_textview.setText(response.code());
                    return;

                }

                Post postResponse = response.body();
                String contents = "";
                contents += "CODE:" + response.code() + "\n";
                contents += "ID: " + postResponse.getId() + "\n";
                contents += "Post id:" + postResponse.getUserId() + "\n";
                contents += "Title:" + postResponse.getTitle() + "\n";
                contents += "Text :" + postResponse.getText() + "\n\n";
                final_textview.append(contents);


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });

    }

    private void initPut() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //  /* need if we want to the log, elese leave it*/.client(okHttpClient)
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Post post = new Post("344", "vcjshgbkvbjsboe", null);
        Call<Post> call = jsonPlaceHolderApi.putPost(21, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    final_textview.setText(response.code());
                    return;

                }

                Post postResponse = response.body();
                String contents = "";
                contents += "CODE:" + response.code() + "\n";
                contents += "ID: " + postResponse.getId() + "\n";
                contents += "Post id:" + postResponse.getUserId() + "\n";
                contents += "Title:" + postResponse.getTitle() + "\n";
                contents += "Text :" + postResponse.getText() + "\n\n";
                final_textview.append(contents);


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });


    }

    private void initHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jengma");
        map.put("class", "12");
        map.put("roll", "199");

        Set<String> keys = map.keySet();
        for (String i : keys) {
            Log.d(TAG, "initHashMap: keys of the map:" + map.get(i));
        }

        Set<Map.Entry<String, String>> values = map.entrySet();
        for (Map.Entry<String, String> e : values) {
            Log.d(TAG, "initHashMap:hashset" + e.getKey() + " and " + e.getValue());
        }

    }

    private void initHashSet() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jengma");
        map.put("class", "12");
        map.put("roll", "199");

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Mr");
        hashSet.add("Jwngma");
        hashSet.add("Basumatary");


        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Log.d(TAG, "initHashSet:FullName " + iterator.next());
        }

    }

    private void initCreatePost() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        /*1*/
        final Post post = new Post("233", "this is the title", "tis is the body, just a dummy one");
        /*2*//*Call<Post> call = jsonPlaceHolderApi.CreatePost(234,"this is title","tgisiis the bodu you want");*/

        Map<String, String> filed = new HashMap<>();
        filed.put("userId", "244");
        filed.put("title", "thisis the title");
        filed.put("body", "0000000tisjsidsvch");

        Call<Post> call = jsonPlaceHolderApi.CreatePost(filed);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText(response.code());
                    return;
                }
                Post postResponse = response.body();
                String contents = "";
                contents += "CODE:" + response.code() + "\n";
                contents += "ID: " + postResponse.getId() + "\n";
                contents += "Post id:" + postResponse.getUserId() + "\n";
                contents += "Title:" + postResponse.getTitle() + "\n";
                contents += "Text :" + postResponse.getText() + "\n\n";
                final_textview.append(contents);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });

    }

    private void initComments() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText(response.code());
                    return;
                }

                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String contents = "";
                    contents += " PostId:" + comment.getPostId() + "\n";
                    contents += " id:" + comment.getId() + "\n";
                    contents += " Name:" + comment.getName() + "\n";
                    contents += " Email:" + comment.getEmail() + "\n";
                    contents += " Comment:" + comment.getBody() + "\n\n";
                    final_textview.append(contents);

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                final_textview.setText(t.getMessage());

            }
        });


    }

    private void initGetpost() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "2");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


        /*Call<Post> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post posts = response.body();
                String userid = posts.getUserId();
                int id = posts.getId();
                String tiyle = posts.getTitle();
                String bod = posts.getText();

                List<Post> list= new ArrayList<>();
                list.add(new Post(userid,tiyle,bod));
                RecyclerView recyclerView= findViewById(R.id.new_recy);
                LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new PostAdapter(list));

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "err"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });*/
        /*Call<List<Post>> call = jsonPlaceHolderApi.getPost(parameters);


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText("Error code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";
                    final_textview.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });*/


        //query array id intergers
  /*      Call<List<Post>> call = jsonPlaceHolderApi.getPost(new Integer[]{4});
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText("Error code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";
                    final_textview.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });*/

        //"ullam ut quidem id aut vel consequuntur"

        //query array Strings title
        Call<List<Post>> call = jsonPlaceHolderApi.getPost(new String[]{"ullam ut quidem id aut vel consequuntur"});
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    final_textview.setText("Error code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";
                    final_textview.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                final_textview.setText(t.getMessage());

            }
        });

    }
}
