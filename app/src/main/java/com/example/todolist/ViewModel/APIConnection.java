package com.example.todolist.ViewModel;

import android.content.Context;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.todolist.Model.ToDoItem;
import com.example.todolist.View.DashboardA;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIConnection {

    public static ArrayList<ToDoItem> toDoItems= new ArrayList<>() ;
    ToDoItem toDoItem ;

    public void getData(Context context){
        DashboardA.progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        "https://62383d6d00ed1dbc5ab04a05.mockapi.io/api/v1/lists",null,new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0;i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                toDoItem = new ToDoItem(jsonObject.getString("createdAt"),jsonObject.getString("name"),jsonObject.getString("priority"));
                                toDoItem.setKey(jsonObject.getString("id"));
                                toDoItems.add(toDoItem);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        DashboardA.adapter.notifyDataSetChanged();
                      DashboardA.progressBar.setVisibility(View.INVISIBLE);

                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DashboardA.progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public   void Post(ToDoItem toDoItem) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://62383d6d00ed1dbc5ab04a05.mockapi.io/api/v1/lists")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

    }
}
