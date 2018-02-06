package ithub.iinventory;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeBillActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar nToolBar;

    private TextView mItemID , mQty, mItemName, mItemPrice, mTotalPrice;
    private FirebaseAuth mAuth;


    private DatabaseReference mRef;

    private Button mAddToBill;
    private Button mContinue;

    private  EditText mCash;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CardView mCardView;

    //    public LayoutInflater inflater = getLayoutInflater();
    DatabaseReference rootRef, itemRef;

    public LayoutInflater inflater;
    public View v;

    private Button mRemoveItem;



    // ViewGroup parent;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_bill);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        //database reference pointing to child node
        itemRef = rootRef.child("Items");

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("Make Bill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        final List list = new ArrayList();

        mItemID = (TextView)findViewById(R.id.itemID);
        mQty = (TextView)findViewById(R.id.qty);
        mAddToBill = (Button)findViewById(R.id.addToBillBtn);
        mTotalPrice = (TextView)findViewById(R.id.totalPriceView);
       // mRemoveItem = (Button) findViewById(R.id.removeItem);
        mItemName = (TextView) findViewById(R.id.itemName);
       // mItemPrice = (TextView) findViewById(R.id.itemPrice);
        mContinue = (Button) findViewById(R.id.contBtn);
        mCash = (EditText) findViewById(R.id.cashEdit);
        mCardView = findViewById(R.id.myCardView);
     //   mRemoveItem = mCardView.findViewById(R.id.removeItem);
  //      mItemPrice = mCardView.findViewById(R.id.itemPrice);
        //////////////////****************/////////
        // here child node of items taken and called according to their model number
        // modelRef = itemRef.child(mItemID.getText().toString());

        ///////////********************////////////

        final ArrayList<ExmapleItem> exampleItem  = new ArrayList<>();

        mAddToBill.setOnClickListener(new View.OnClickListener() {
            /// to equalize to model number use toEqual() and thereafter to go through all the data to take item name and item price go through all the databses
            //// use two for loops to take item name and item price values
            ///// see the database image send by akila to do above things (some info are book marked)

            @Override
            public void onClick(View v) {

                // here according to above case no need of equalTo() value

                itemRef.child(mItemID.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Map<String, String> map = (Map)dataSnapshot.getValue();

                        String itemName = map.get("iName");
                        String itemPrice = map.get("iPrice");
                        //    boolean  itemPrice =  map.get("iPrice").isEmpty();

                        int oneItemPrice = Integer.parseInt(itemPrice);
                        int qty = Integer.parseInt(mQty.getText().toString());

                        String totalPrice = Integer.toString(oneItemPrice * qty);
                        //String totalPrice = map.get("iPrice");

                        exampleItem.add(new ExmapleItem(itemName, totalPrice));

                        sum += Integer.parseInt(totalPrice);
                        mTotalPrice.setText(sum+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                        // ...
                    }
                });
            }
        });


        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mCash.getText().toString().isEmpty()) {
                    int cash = (Integer.parseInt(mCash.getText().toString().trim()) - Integer.parseInt(mTotalPrice.getText().toString().trim()));

                    Intent intent = new Intent(MakeBillActivity.this, CustomLayout.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_TOTAL", mTotalPrice.getText().toString());
                    extras.putString("EXTRA_CASH", mCash.getText().toString());
                    extras.putString("EXTRA_BALANCE", Integer.toString(cash));
                    intent.putExtras(extras);
                    startActivity(intent);
                    //finish();
                }

                else {
                    Toast.makeText(MakeBillActivity.this,"You must enter the Cash value before continue",Toast.LENGTH_SHORT).show();
                }
            }
        });

/*
        mCardView.findViewById(R.id.removeItem).setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {//  MakeBillActivity billObj = new MakeBillActivity();
        // int sumValue = billObj.getSum();
        // final ExmapleItem currentItem = mExampleList.get(position);

        // String equipPrice = holder.mItemPrice.getText().toString();
        // String equipPrice = currentItem.getItemPrice();
        String equipPrice = mItemPrice.getText().toString();
        sum -= Integer.parseInt(equipPrice);
        //  billObj.setSum(sumValue);
        //  innerView.setText("LKR"+ sumValue +"/=");
        //  holder.mTotal.setText("LKR"+ sumValue +"/=");
        //     mTotal.setText("LKR"+ sumValue +"/=");;
    }
});

*/
        mRecyclerView = findViewById(R.id.itemListView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleItem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



    }

    public int getSum(){
        return sum;
    }


    public void setSum(int sum){
        this.sum = sum;
    }
}
