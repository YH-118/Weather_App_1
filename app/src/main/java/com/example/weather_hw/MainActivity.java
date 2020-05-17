package com.example.weather_hw;

import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //EditText editText;
    //Button button;
    //TextView txtcoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private static final String location = "";



    public void parseName (View view) {
        Intent nameIntent = new Intent(this, Main2Activity.class);

        EditText text = findViewById(R.id.editText);

        String name = text.getText().toString();

        nameIntent.putExtra(location,name); //string name, string value

        startActivity(nameIntent);
    }

}
