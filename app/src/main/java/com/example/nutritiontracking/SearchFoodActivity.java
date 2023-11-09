package com.example.nutritiontracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nutritiontracking.DataModel.FoodModel;
import com.example.nutritiontracking.Recycler.SearchFoodRecyclerAdapter;
import com.example.nutritiontracking.Utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFoodActivity extends AppCompatActivity {
    //1- AdapterView: RecyclerView
    RecyclerView addFoodRecyclerView;

    //2- Data Source:
    //FoodModel[] myFoodListData;
    //FoodModel[] myFoodSearchList;
    //private ArrayList<FoodModel> foodSearchList;
    List<FoodModel> foodSearchList = new ArrayList<>();

    //3- Adapter
    SearchFoodRecyclerAdapter adapter;

    private RequestQueue mQueue;

    SearchView foodSearch;

    private boolean recyclerCreated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        recyclerCreated = false;
        addFoodRecyclerView = findViewById(R.id.recycler_addFood);
        foodSearch = findViewById(R.id.searchView_foodSearch);
        foodSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                    jsonParse(query);

                return true;
                //return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*if (newText.length() > 2) {
                    jsonParse(newText);
                }
                return true;*/
                return false;
            }
        });

        //mQueue = Volley.newRequestQueue(this);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        //jsonParse();
        //fetchMovies();

    }



    private void jsonParse(String query) {

        if (recyclerCreated == true) {
            foodSearchList.clear();
            addFoodRecyclerView.getAdapter().notifyDataSetChanged();
        }

        String url = "https://trackapi.nutritionix.com/v2/search/instant?query=" + query;
        // + "&detailed=true"

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // You can parse the JSON response here
                try {
                    JSONArray jsonArray = response.getJSONArray("common");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String food_name = jsonObject.getString("food_name");
                        String serving_unit = jsonObject.getString("serving_unit");
                        int serving_qty = jsonObject.getInt("serving_qty");
                        String imgUrl = jsonObject.getJSONObject("photo").getString("thumb");
                        //boolean branded = false;
                        String brand = "Brand: N/A";
                        String nix_item_id = "N/A";

                        FoodModel foodSearchObj = new FoodModel(food_name, serving_unit, serving_qty, imgUrl, brand, nix_item_id);
                        foodSearchList.add(foodSearchObj);
                        //textview.setText(food_name + " " + serving_unit + " " + imgUrl + " " + nix_item_id);
                    }

                    /*
                    JSONArray jsonArray_branded = response.getJSONArray("branded");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray_branded.getJSONObject(i);
                        String food_name = jsonObject.getString("food_name");
                        String serving_unit = jsonObject.getString("serving_unit");
                        int serving_qty = jsonObject.getInt("serving_qty");
                        String imgUrl = jsonObject.getJSONObject("photo").getString("thumb");
                        String brand = "Brand name: " + jsonObject.getString("brand_name");
                        String nix_item_id = jsonObject.getString("nix_item_id");

                        FoodModel foodSearchObj = new FoodModel(food_name, serving_unit, serving_qty, imgUrl, brand, nix_item_id);
                        foodSearchList.add(foodSearchObj);
                        //textview.setText(food_name + " " + serving_unit + " " + imgUrl + " " + nix_item_id);
                    }*/


                } catch (JSONException e) {
                    //textview.setText(e.toString());
                }
                //Adapter
                if (recyclerCreated == false) {
                    adapter = new SearchFoodRecyclerAdapter(SearchFoodActivity.this,foodSearchList);
                    addFoodRecyclerView.setHasFixedSize(true);
                    addFoodRecyclerView.setLayoutManager(new LinearLayoutManager(SearchFoodActivity.this));
                    addFoodRecyclerView.setAdapter(adapter);
                    recyclerCreated = true;
                } else {
                    addFoodRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // In case of error
                error.printStackTrace();
                //textview.setText(error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-app-id", "e8938e5f");
                params.put("x-app-key", "d8708f1ba07eeb826dc13562a35c3b5b");
                return params;
            }
        };

        mQueue.add(request);
    }
}
