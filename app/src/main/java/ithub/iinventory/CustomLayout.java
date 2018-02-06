package ithub.iinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class CustomLayout extends AppCompatActivity {

    TextView totalValue;
    TextView cashValue;
    TextView balanceValue;
    Button saveContBtn;
    Button cancelBtn;

    private android.support.v7.widget.Toolbar nToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView dateView = (TextView)findViewById(R.id.dateView);
        dateView.setText(currentDate);

        getSupportActionBar().setTitle("Bill Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        totalValue = (TextView) findViewById(R.id.totalValue);
        cashValue = (TextView) findViewById(R.id.cashValue);
        balanceValue = (TextView) findViewById(R.id.balanceValue);
        saveContBtn = (Button) findViewById(R.id.saveAndContBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        Bundle extras = getIntent().getExtras();
        String totalString = extras.getString("EXTRA_TOTAL");
        String cashString = extras.getString("EXTRA_CASH");
        String balanceString = extras.getString("EXTRA_BALANCE");

        totalValue.setText("LKR "+totalString+"/=");
        cashValue.setText("LKR "+cashString+"/=");
        balanceValue.setText("LKR "+balanceString+"/=");

        saveContBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomLayout.this, PrinterService.class);
                startActivity(intent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomLayout.this, MakeBillActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
