package com.example.weather_hw;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Scanner;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        showData();
    }

    private static final String LATLNG = "";
//    private static final String LAT= "";
//    private static final String LNG= "";

    public void showData(){
        String latlng = getIntent().getStringExtra(LATLNG);
        String coordinate = latlng;
        GetData getdata = new GetData();
        getdata.execute(coordinate);
    }

    public void parseLoc(View view){
        Intent mapIntent = new Intent(this, MapsActivity.class);
        int index = LATLNG.indexOf(",");
        String coord1 = LATLNG.substring(0,index-1);
        String coord2 = LATLNG.substring(index+1);
        mapIntent.putExtra("lat",coord1);
        mapIntent.putExtra("lng",coord2);

        startActivity(mapIntent);
    }

    private class GetData extends AsyncTask<String, Void, String> {
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
            //String response;
            try {
                String coordinate = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://api.darksky.net/forecast/a2e05739a5bc30b2503c7ecf36c793fa/");
                url = url.concat(coordinate);
                //response = http.getHTTPData(url);
                URL urladdress = new URL(url);
                Scanner scan = new Scanner(urladdress.openStream());
                String str = new String();
                while (scan.hasNext()) {
                    str += scan.nextLine();
                }
                scan.close();

                return str;
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject json = new JSONObject(s);
                double temp = json.getJSONObject("currently").getDouble("temperature");
                double hum = json.getJSONObject("currently").getDouble("humidity");
                double wind = json.getJSONObject("currently").getDouble("windSpeed");
                double precipProbability = json.getJSONObject("currently").getDouble("precipProbability");
                String precipType;
                if(json.has("precipType")){
                    precipType = json.getJSONObject("currently").getString("precipType");
                }else{
                    precipType = "null";
                }

                TextView show_data = findViewById(R.id.dataText);
                show_data.setText(String.format(
                        "Temperature: %s\nHumidity: %s\nWind Speed: %s\nPrecipitation Probability: %s\nPrecipitation Type: %s\n",
                        Double.toString(temp), Double.toString(hum), Double.toString(wind), Double.toString(precipProbability), precipType));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
