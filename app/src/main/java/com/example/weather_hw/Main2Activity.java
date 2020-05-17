package com.example.weather_hw;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showName();
        showAddress();
    }


    private static final String location = "";
    private static final String LATLNG = "";
    double lat;
    double lng;

    public void showName() {
        TextView show_name = findViewById(R.id.textView);

        String name = getIntent().getStringExtra(location);

        show_name.setText(name);
    }

    public void showAddress() {
        TextView show_address = findViewById(R.id.textView);
        GetCoord getcoord = new GetCoord();
        getcoord.execute(show_address.getText().toString().replace(" ", "+"));
    }


    private class GetCoord extends AsyncTask<String, Void, String> {
        /*
        ProgressDialog dialog = new ProgressDialog(Main2Activity.this);

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        */


        @Override
        protected String doInBackground(String... strings) {
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                url = url.concat("&key=AIzaSyAMvgFTUfg1brfjS5IUQ0d7d7UHTjI1ICg");
                response = http.getHTTPData(url);

                return response;
            }
            catch (Exception ex){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                TextView show_address = findViewById(R.id.msgView);
                show_address.setText(String.format("Coordinates: %s , %s", Double.toString(lat), Double.toString(lng)));


                /*
                if(dialog.isShowing())

                    dialog.dismiss();
                     */

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }



    public void parseCoord(View view){
        Intent locIntent = new Intent(this, Main3Activity.class);

        TextView text = (TextView)findViewById(R.id.msgView);

        String name = text.getText().toString();

        locIntent.putExtra("lng",lng);
        locIntent.putExtra("lat",lat);

        startActivity(locIntent);
    }
}
