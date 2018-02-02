package ithub.iinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MakeBillActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;

    private TextView mItemID , mQty ;
    private FirebaseAuth mAuth;

    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference usersReference;

    private Button mAddToBill;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private Button mRemoveItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_bill);


        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("Make Bill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
       // mRef = mFirebaseDatabase.getReference();

        mItemID = (TextView)findViewById(R.id.itemID);
        mQty = (TextView)findViewById(R.id.qty);
        mAddToBill = (Button)findViewById(R.id.addToBillBtn);

        String newItemIdByUser = mItemID.getText().toString().trim();


        usersReference = FirebaseDatabase.getInstance().getReference().child("SahanTestItems").child("0003");




        final ArrayList<ExmapleItem> exampleItem = new ArrayList<>();


        mAddToBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                                String itemName = dataSnapshot.child("iName").getValue().toString();
                                String itemPrice = dataSnapshot.child("iPrice").getValue().toString();




                                exampleItem.add(new ExmapleItem(itemName, itemPrice));
                                // exampleItem.add(new ExmapleItem(getItems.getItemID(),getItems.getItemPrice()));




                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                mRecyclerView = findViewById(R.id.itemListView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(MakeBillActivity.this);
                mAdapter = new ExampleAdapter(exampleItem);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);


            }

        });



       /* final ArrayList<ExmapleItem> exampleItem  = new ArrayList<>();
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
        exampleItem.add(new ExmapleItem("Gaming Bag Pack","LKR 5500/=")); */






    }
}
