package com.android.androidcrudjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Tipo de lista que já é propriedade do android
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
    }
}
