package ithub.iinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_ItemsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference usersReference;

    private ImageButton mSearchButton;
    private EditText mSearchItemID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__items);

        mAuth = FirebaseAuth.getInstance();

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("All Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchButton = (ImageButton)findViewById(R.id.searchButton);
        mSearchItemID = (EditText)findViewById(R.id.searchItemID);

        final ArrayList<ExmapleItem> exampleItem = new ArrayList<>();

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemID = mSearchItemID.getText().toString();
                usersReference = FirebaseDatabase.getInstance().getReference().child("Items").child(itemID);

                usersReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String itemName = dataSnapshot.child("iName").getValue().toString();
                        String itemPrice = dataSnapshot.child("iPrice").getValue().toString();

                        exampleItem.add(new ExmapleItem(itemName, itemPrice));
                        mRecyclerView = findViewById(R.id.itemListView);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(All_ItemsActivity.this);
                        mAdapter = new ExampleAdapter(exampleItem);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });






     /*  ArrayList<ExmapleItem> exampleItem  = new ArrayList<>();
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
        mRecyclerView.setAdapter(mAdapter);*/




    }
}
