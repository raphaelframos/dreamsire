package com.powellapps.dreamsire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.powellapps.dreamsire.fragments.DesejosFragment;
import com.powellapps.dreamsire.fragments.LoginFragment;
import com.powellapps.dreamsire.fragments.PerfilFragment;
import com.powellapps.dreamsire.utils.ConstantsUtils;
import com.powellapps.dreamsire.utils.FragmentUtils;

public class PrincipalActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonNovo;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentUtils.replace(PrincipalActivity.this, R.id.fragment_principal, new DesejosFragment());
                    return true;

                case R.id.navigation_notifications:
                    FragmentUtils.replace(PrincipalActivity.this, R.id.fragment_principal, new LoginFragment());

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        MobileAds.initialize(this, getString(R.string.admob));

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        floatingActionButtonNovo = (FloatingActionButton) findViewById(R.id.floatingNovo);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        floatingActionButtonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), NovoDesejoActivity.class), ConstantsUtils.NOVO_DESEJO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ConstantsUtils.NOVO_DESEJO){
            if(resultCode == RESULT_OK){
                navigation.setSelectedItemId(R.id.navigation_home);
            }
        }else if(requestCode == ConstantsUtils.LOGIN){
            if(resultCode == RESULT_OK){
                FragmentUtils.replace(PrincipalActivity.this, R.id.fragment_principal, new PerfilFragment());
            }
        }
    }
}
