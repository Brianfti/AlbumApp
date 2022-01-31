package com.example.albumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity implements AdapterActivity.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private AdapterActivity mAdapterActivity;
    private ArrayList<ItemsActivity> mItemsActivityList;
    private RequestQueue mRequestQueue;
    private String url = "https://jsonplaceholder.typicode.com/photos";
    public static final String EXTRA_URL = "urlImg";
    public static final String EXTRA_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mItemsActivityList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        JsonParse();
    }

    private void JsonParse() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject itemObject = response.getJSONObject((i));
                        ItemsActivity itemsActivity = new ItemsActivity();
                        itemsActivity.setTitle(itemObject.getString("title").toString());
                        itemsActivity.setUrlImg(itemObject.getString("url"));
                        mItemsActivityList.add(itemsActivity);
                    }   catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mAdapterActivity = new AdapterActivity(getApplicationContext(), mItemsActivityList);
                mRecyclerView.setAdapter(mAdapterActivity);
                mAdapterActivity.setOnItemClickListener(AlbumActivity.this);
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(request);
    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, ImageActivity.class);
        ItemsActivity clickedItem = mItemsActivityList.get(position);
        i.putExtra(EXTRA_URL, clickedItem.getUrlImg());
        i.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        startActivity(i);
    }
}