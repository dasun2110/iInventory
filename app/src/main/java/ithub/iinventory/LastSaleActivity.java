package ithub.iinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LastSaleActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_sale);

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("Last Sale");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
