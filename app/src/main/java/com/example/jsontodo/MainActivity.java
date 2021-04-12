package com.example.jsontodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jsontodo.Model.ToDo;
import com.example.jsontodo.ToDo_Adapter.ToDoListAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView RecyclerViewToDoList;
    List<ToDo> toDoList = new ArrayList<>();

    Executor executor;
    ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewToDoList = findViewById(R.id.RecyclerViewToDoList);

        executor = Executors.newSingleThreadExecutor();

        //Initialize For JSON
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/todos")
                .build();

        // CallBack Methods
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String result = response.body().string(); // get body of json
                try {
                    JSONArray jsonArray = new JSONArray(result); // get jason data with array


                    for (int i = 0; i < jsonArray.length(); i++) {
                        ToDo toDo = new ToDo(); // jason class
                        JSONObject jsonObject = jsonArray.getJSONObject(i); // each jason array convert to an object
                        toDo.setTitle(jsonObject.getString("title"));
                        toDo.setUserId(jsonObject.getInt("userId"));
                        toDo.setCompleted(jsonObject.getBoolean("completed"));
                        toDo.setId(jsonObject.getInt("id"));

                        toDoList.add(toDo);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new ToDoListAdapter(toDoList, MainActivity.this);
                            RecyclerViewToDoList.setAdapter(adapter);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            RecyclerViewToDoList.setLayoutManager(linearLayoutManager);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}