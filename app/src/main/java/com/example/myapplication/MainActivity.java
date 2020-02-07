
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    List<PicDataModel> dataModelList  = new ArrayList<>();
    private RecyclerView mImageListView;
    private ProgressBar simpleProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleProgressBar =  findViewById(R.id.progressBar);

        mImageListView = findViewById(R.id.thumbnelList);



        new GetimageData().execute();


    }


    private class GetimageData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://picsum.photos/list";
            /** here wo call method makeServiceCall from HttpHandeller class which accepts parameter as string*/
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    /** here we pass response to json array to map it with json format*/
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    /** looping through All data*/

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dataObj = jsonArray.getJSONObject(i);
                        String id = dataObj.getString("id");
                        String fileName = dataObj.getString("filename");
                        String author = dataObj.getString("author");


                        String imgUrl = "https://picsum.photos/300/300?image="+id;

                        URL newUrl = new URL(imgUrl);
                        Bitmap mIcon_val = BitmapFactory.decodeStream(  newUrl.openConnection() .getInputStream());

                       /**  adding each cell data to  list */
                        dataModelList.add(new PicDataModel(id,author,mIcon_val));

                        Log.d(TAG, "doInBackground: dat added "+i+" Times");
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            simpleProgressBar.setVisibility(View.GONE);

            mImageListView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2 ));
            PicsAdapter picsAdapter = new PicsAdapter(dataModelList);
            mImageListView.setAdapter(picsAdapter);


        }


    }




}
