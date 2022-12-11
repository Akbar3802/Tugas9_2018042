package com.example.tugas6_alarmmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tugas6_alarmmanager.databinding.ActivityDashboardBinding;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    private PendingIntent pendingIntent;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mengaktifkan view binding
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Action Bar
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Intent a = new Intent(Dashboard.this, Dashboard.class);
                    startActivity(a);
                }else if (id == R.id.nav_alarm) {
                    Intent a = new Intent(Dashboard.this, MyAlarmMain.class);
                    startActivity(a);
                } else if (id == R.id.nav_buku) {
                    Intent a = new Intent(Dashboard.this, DestinationActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_profile) {
                    Intent a = new Intent(Dashboard.this, Profil1.class);
                    startActivity(a);
                } else if (id == R.id.nav_tambahdata) {
                    Intent a = new Intent(Dashboard.this, TambahData.class);
                    startActivity(a);
                } else if (id == R.id.nav_listbuku) {
                    Intent a = new Intent(Dashboard.this, Splash.class);
                    startActivity(a);
                }
                return true;
            }
        });
        //button Alarm Manager
        binding.Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAlarm();
            }
        });

        binding.Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDataBuku();
            }
        });

        binding.Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuProfil();
            }
        });
        binding.Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuTambahData();
            }
        });
        binding.Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuListData();
            }
        });
    }
    public void menuAlarm() {
        Intent menuAlarm= new Intent(Dashboard.this, MyAlarmMain.class);
        startActivity(menuAlarm);
    }
    public void menuDataBuku() {
        Intent menuDataBuku = new Intent(Dashboard.this, DestinationActivity.class);
        startActivity(menuDataBuku);
    }
    public void menuProfil() {
        Intent menuProfil = new Intent(Dashboard.this, Profil1.class);
        startActivity(menuProfil);
    }
    public void menuTambahData() {
        Intent menuTambahData = new Intent(Dashboard.this, TambahData.class);
        startActivity(menuTambahData);
    }
    public void menuListData() {
        Intent menuListData = new Intent(Dashboard.this, Splash.class);
        startActivity(menuListData);
    }
    //action Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
