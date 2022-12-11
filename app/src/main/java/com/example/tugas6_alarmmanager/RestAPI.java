package com.example.tugas6_alarmmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.tugas6_alarmmanager.databinding.ActivityMainRestapiBinding;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class RestAPI extends AppCompatActivity implements
        View.OnClickListener {
    //declaration variable
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityMainRestapiBinding binding;
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityMainRestapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Intent a = new Intent(RestAPI.this, Dashboard.class);
                    startActivity(a);
                }else if (id == R.id.nav_alarm) {
                    Intent a = new Intent(RestAPI.this, MyAlarmMain.class);
                    startActivity(a);
                } else if (id == R.id.nav_buku) {
                    Intent a = new Intent(RestAPI.this, DestinationActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_profile) {
                    Intent a = new Intent(RestAPI.this, Profil1.class);
                    startActivity(a);
                } else if (id == R.id.nav_tambahdata) {
                    Intent a = new Intent(RestAPI.this, TambahData.class);
                    startActivity(a);
                } else if(id ==R.id.nav_listbuku){
                    Intent a = new Intent(RestAPI.this, Splash.class);
                    startActivity(a);
                }
                return true;
            }
        });

    }

    //onclik button fetch
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fetch_button) {
            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://run.mocky.io/v3/33684692-960e-4f5c-91b2-9df81f12a17b")
                .buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }

    class DOTask extends AsyncTask<URL, Void, String> {
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //get data json
        public void parseJson(String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject innerObj = jsonObject.getJSONObject("data");
            JSONArray cityArray = innerObj.getJSONArray("data");
            for (int i =0; i <cityArray.length(); i++){
                JSONObject obj = cityArray.getJSONObject(i);
                String Sobj = obj.get("id").toString();
                if (Sobj.equals(index)){
                    String id = obj.get("id").toString();
                    String title = obj.get("title").toString();
                    String genre = obj.get("genre").toString();
                    String publishedOn = obj.get("publishedOn").toString();
                    String authors = obj.get("authors").toString();
                    String pages= obj.get("pages").toString();
                    String isbn = obj.get("isbn" ).toString();
                    String bestFor = obj.get("bestFor" ).toString();
                    String publisher = obj.get("publisher" ).toString();
                    String description = obj.get("description" ).toString();

                    binding.resultId.setText(id);
                    binding.resultTitle.setText(title);
                    binding.resultGenre.setText(genre);
                    binding.resultPublishedOn.setText(publishedOn);
                    binding.resultAuthors.setText(authors);
                    binding.resultPages.setText(pages);
                    binding.resultIsbn.setText(isbn);
                    binding.resultBestFor.setText(bestFor);
                    binding.resultPublisher.setText(publisher);
                    binding.resultDescription.setText(description);

                    break;
                }
                else{
                    binding.resultTitle.setText("Not Found");
                }
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}