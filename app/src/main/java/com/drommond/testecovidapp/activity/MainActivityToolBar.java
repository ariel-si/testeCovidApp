package com.drommond.testecovidapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.drommond.testecovidapp.R;
import com.drommond.testecovidapp.configs.ConfiguracaoFirebase;
import com.drommond.testecovidapp.fragments.InformacaoFragment;
import com.drommond.testecovidapp.fragments.ListaOngsFragment;
import com.drommond.testecovidapp.fragments.TesteFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivityToolBar extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = ConfiguracaoFirebase.getAuth();

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Covid Teste App");
        setSupportActionBar(toolbar);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("cuide-se", InformacaoFragment.class)
                .add("Teste", TesteFragment.class)
                .add("ajude", ListaOngsFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair :
                deslogarUsuario();
                finish();
            break;
            case R.id.menuCreditos :
                telaCreditos();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario() {
        try{
            auth.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void telaCreditos(){
        Intent intent = new Intent(MainActivityToolBar.this, CreditosActivity.class);
        startActivity(intent);
    }

}