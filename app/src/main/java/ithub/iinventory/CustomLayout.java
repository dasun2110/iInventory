package ithub.iinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomLayout extends AppCompatActivity {

    TextView totalValue;
    TextView cashValue;
    TextView balanceValue;
    Button saveContBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

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
