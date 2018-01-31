package ithub.iinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;


    private Button mAddItem , mMakeBill , mAllItems ,mChatBtn;

    private DatabaseReference usersReference;

    private TextView mUserName;

    private FirebaseUser mCurrentUser;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){
            sendToStart();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView dateView = (TextView)findViewById(R.id.dateView);
        dateView.setText(currentDate);


        mAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("i Inventory");

        mAddItem = (Button) findViewById(R.id.mainAddItem);
        mMakeBill = (Button) findViewById(R.id.mainMakeBill);
        mAllItems = (Button)findViewById(R.id.mainAllItem);
        mChatBtn = (Button)findViewById(R.id.chatButton);
        mUserName = (TextView)findViewById(R.id.loggedUser);

        mCurrentUser= FirebaseAuth.getInstance().getCurrentUser();


        //Error on this code - NullPointException on installing and get user id

      /*  final String userID =mAuth.getCurrentUser().getUid();
        usersReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String loggedUserName = dataSnapshot.child("name").getValue().toString();
                mUserName.setText(loggedUserName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */



        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(MainActivity.this,AddItemActivity.class);
                startActivity(addItem);

            }
        });

        mMakeBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent makeBill = new Intent(MainActivity.this,MakeBillActivity.class);
                startActivity(makeBill);
            }
        });

        mChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(MainActivity.this,ChatActivity.class);
                startActivity(chatIntent);
            }
        });

        mAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allItemIntent = new Intent(MainActivity.this,All_ItemsActivity.class);
                startActivity(allItemIntent);
            }
        });









    }

    private void sendToStart() {
        Intent mainIn = new Intent(MainActivity.this,StartActivity.class);
        startActivity(mainIn);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);


         getMenuInflater().inflate(R.menu.main_menu,menu);

         return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_logoutBtn){
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }


        return true;
    }
}
