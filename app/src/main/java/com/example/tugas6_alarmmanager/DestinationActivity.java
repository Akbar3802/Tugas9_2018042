package com.example.tugas6_alarmmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.tugas6_alarmmanager.Splash;
import com.example.tugas6_alarmmanager.databinding.ActivityDestinationBinding;
import com.example.tugas6_alarmmanager.databinding.ActivityTambahdataBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class DestinationActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityDestinationBinding binding;

    RecyclerView recylerView;

    String s1[], s2[], s3[];
    int images[] = {R.drawable.bukualadin, R.drawable.buku100, R.drawable.ensiklopedia, R.drawable.anakbetawi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        binding = ActivityDestinationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork("Notifikasi", ExistingWorkPolicy.REPLACE, request);
            }
        });

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
                    Intent a = new Intent(DestinationActivity.this, Dashboard.class);
                    startActivity(a);
                }else if (id == R.id.nav_alarm) {
                    Intent a = new Intent(DestinationActivity.this, MyAlarmMain.class);
                    startActivity(a);
                } else if (id == R.id.nav_buku) {
                    Intent a = new Intent(DestinationActivity.this, DestinationActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_profile) {
                    Intent a = new Intent(DestinationActivity.this, Profil1.class);
                    startActivity(a);
                } else if (id == R.id.nav_tambahdata) {
                    Intent a = new Intent(DestinationActivity.this, TambahData.class);
                    startActivity(a);
                } else if(id ==R.id.nav_listbuku){
                Intent a = new Intent(DestinationActivity.this, Splash.class);
                startActivity(a);
                }
                    return true;
        }
    });
    recylerView =

    findViewById(R.id.recyclerView);

    s1 =

    getResources().

    getStringArray(R.array.buku);

    s2 =

    getResources().

    getStringArray(R.array.deskripsi);

    s3 =

    getResources().

    getStringArray(R.array.star);

    DataBuku appAdapter = new DataBuku(this, s1, s2, s3, images);
        recylerView.setAdapter(appAdapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(DestinationActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setItemAnimator(new

    DefaultItemAnimator());
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}