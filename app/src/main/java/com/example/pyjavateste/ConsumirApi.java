package com.example.pyjavateste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.pyjavateste.databinding.ActivityConsumirApiBinding;
import com.squareup.picasso.Picasso;

public class ConsumirApi extends AppCompatActivity {

    private ActivityConsumirApiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsumirApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if( !Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyObjt = py.getModule("script2");

        binding.button2.setOnClickListener(view -> {

            String url = pyObjt.callAttr("main").toString();

            Picasso.get().load(url).into(binding.imageView);
        });
    }

    /*
     * Configurações de menu
     * */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.consumir:
                finish();
                startActivity(new Intent(getApplicationContext(), ConsumirApi.class));
                break;
            case R.id.soma:
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}