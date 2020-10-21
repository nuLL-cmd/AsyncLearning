package com.automatodev.asynclearning;

import android.os.AsyncTask;
import android.util.Log;

public class ScondPlane extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        Log.d("logx","Segundo plano");
        return null;
    }

}
