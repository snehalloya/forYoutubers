package com.example.snehal.moviepoject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PAGE = "page";
    private static String url = "http://www.omdbapi.com/?i=tt3896198&apikey=77dc6a9e";
    List<DataModel> list = new ArrayList<>();
    ArrayList<String> titList = new ArrayList<>();
    ArrayList<String> dirList = new ArrayList<>();
    ArrayList<String> potList = new ArrayList<>();
    JSONObject object;
    InputStream is;
    String json;
    ProgressDialog dialog;
    JSONObject jsonObject = new JSONObject();
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new jsonParsers().execute();
    }

    private class jsonParsers extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("getting data");
            dialog.setIndeterminate(false);
            dialog.setCancelable(true);
            dialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... strings) {


            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            HttpResponse httpResponse = null;
            try {
                httpResponse = client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            try {
                is = httpEntity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            StringBuilder str = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    str.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            json = str.toString();
            try {
                object = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return object;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

            DataModel model = new DataModel();
            List<DataModel> list = new ArrayList<>();

            String Name = s.optString("Title");
            Log.e("check title", "" + Name);
            model.setName(s.optString("Title"));
            model.setDirector(s.optString("Director"));
            model.setPoster(s.optString("Poster"));
            list.add(model);

            for (int i = 0; i < list.size(); i++) {
                String title = list.get(i).getName();
                String director = list.get(i).getDirector();
                String poster = list.get(i).getPoster();
                titList.add(title);
                dirList.add(director);
                potList.add(poster);
                MovieFragment fragment = new MovieFragment();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("Tit", titList);
                bundle.putStringArrayList("Direct", dirList);
                bundle.putStringArrayList("Post", potList);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();

            }

        }

    }
}


