package com.android.dlaundryaja.Activity.PageAdmin.Akun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.dlaundryaja.Activity.PageAdmin.Dashboard.AdmDashboardActivity;
import com.android.dlaundryaja.Activity.PageAdmin.Laporan.AdmLaporanActivity;
import com.android.dlaundryaja.Login.LoginActivity;
import com.android.dlaundryaja.R;
import com.android.dlaundryaja.Utils.Controller.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class AdmAkunActivity extends AppCompatActivity {

    private static final String TAG = AdmAkunActivity.class.getSimpleName() ;
    SessionManager sessionManager;
    String getID;
    SharedPreferences sharedPreferences;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_akun);

        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getID = user.get(SessionManager.ID);

        logout = findViewById(R.id.bt_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.buttom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.adm_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adm_dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                AdmDashboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.report:
                        startActivity(new Intent(getApplicationContext(),
                                AdmLaporanActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.adm_account:
//                        startActivity(new Intent(getApplicationContext(),
//                                AdmAkunActivity.class));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void Logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getResources().getString(R.string.prefLoginState),"LoggedOut");
        editor.apply();
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}