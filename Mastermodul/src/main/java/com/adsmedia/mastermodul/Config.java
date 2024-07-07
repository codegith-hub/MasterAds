package com.adsmedia.mastermodul;

import static com.adsmedia.mastermodul.RestApi.keyLogic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.startapp.sdk.adsbase.StartAppSDK;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Config {
    public static ArrayList<String> idKey;
    public static void loadKey(Activity activity,int keyPos){
        idKey = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                RestApi.LoadData(RestApi.SetData(keyLogic)), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                        String keyID = jo.getString("key_id");
                        idKey.add(keyID);
                    }
                    StartAppSDK.init(activity, idKey.get(keyPos), false);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
}
