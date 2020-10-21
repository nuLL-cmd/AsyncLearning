package com.automatodev.asynclearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("logx", "MainActivity1");
        ScondPlane plane = new ScondPlane();
        Log.d("logx", "MainActivity2");
        plane.execute();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/estados/")
                .addConverterFactory(ScalarsConverterFactory.create()).build();

        ApiMunicipios m = retrofit.create(ApiMunicipios.class);
        Call<String> call = m.getAll(52);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("logx","Response: "+response.body());
                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.d("logx","Id: "+object.getString("id"));
                        Log.d("logx","Cidade: "+object.getString("nome"));
                        Log.d("logx","#############");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("logx","Error: "+throwable.getMessage());
            }
        });

    }

}