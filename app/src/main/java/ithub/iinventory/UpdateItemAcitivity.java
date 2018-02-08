package ithub.iinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateItemAcitivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_acitivity);

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("Update Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
