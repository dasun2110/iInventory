package ithub.iinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class All_ItemsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__items);

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("All Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ArrayList<ExmapleItem> exampleItem  = new ArrayList<>();
        exampleItem.add(new ExmapleItem("Marvo Keyboard","LKR 2500/="));
        exampleItem.add(new ExmapleItem("Marvo Mouse","LKR 1500/="));
        exampleItem.add(new ExmapleItem("Samsung SSD EVO 250GB","LKR 25000/="));
        exampleItem.add(new ExmapleItem("Samsung Monitor 24","LKR 35000/="));
        exampleItem.add(new ExmapleItem("Gaming Pad","LKR 250/="));
        exampleItem.add(new ExmapleItem("Gaming Headset Marvo","LKR 35000/="));
        exampleItem.add(new ExmapleItem("Huawei Power Bank 10000mAH","LKR 5000/="));
        exampleItem.add(new ExmapleItem("AC Power Adapter","LKR 25000/="));
        exampleItem.add(new ExmapleItem("Apple Magic Mouse","LKR 11000/="));
        exampleItem.add(new ExmapleItem("HyperX 8GB Memory","LKR 10000/="));
        exampleItem.add(new ExmapleItem("Toshiba 1TB Portable","LKR 11000/="));
        exampleItem.add(new ExmapleItem("Gaming Bag Pack","LKR 5500/="));
        exampleItem.add(new ExmapleItem("Gaming Bag Pack XL","LKR 6000/="));

        mRecyclerView = findViewById(R.id.itemListView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleItem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }
}
