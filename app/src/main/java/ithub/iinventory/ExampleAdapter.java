package ithub.iinventory;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Akila Devinda on 1/31/2018.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    //public TextView mTotal;

    private ArrayList<ExmapleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mItemName;
        public TextView mItemPrice;
        public Button mRemoveButton;
         //public TextView mTotal;
      //  public Activity mMakeBillActivity;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.itemName);
            mItemPrice = itemView.findViewById(R.id.itemPrice);
            mRemoveButton = itemView.findViewById(R.id.removeItem);


        }

    }


    public ExampleAdapter(ArrayList<ExmapleItem> exampleList){
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_items,parent,false);

        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final ExampleViewHolder holder, final int position) {

        final ExmapleItem currentItem = mExampleList.get(position);

        holder.mItemName.setText(currentItem.getItemName());
        holder.mItemPrice.setText(currentItem.getItemPrice());

    //    LayoutInflater inflater = (LayoutInflater) MakeBillActivity.class.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    //    View nv  = LayoutInflater.from().inflate(R.layout.activity_make_bill,null);
     //   ConstraintLayout conLay = nv.findViewById(R.id.conLayout);
     //   final TextView mTotalTxt = conLay.findViewById(R.id.totalPriceView);

        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MakeBillActivity billObj = new MakeBillActivity();
                int sumValue = billObj.getSum();

                // String equipPrice = holder.mItemPrice.getText().toString();
                String equipPrice = currentItem.getItemPrice();
                sumValue -= Integer.parseInt(equipPrice);
                //  billObj.setSum(sumValue);
                //  innerView.setText("LKR"+ sumValue +"/=");
               //  holder.mTotal.setText("LKR"+ sumValue +"/=");
              //     mTotal.setText("LKR"+ sumValue +"/=");;
            //        mTotalTxt.setText(sumValue);
                // Toast.makeText(billObj, Integer.toString(sum), Toast.LENGTH_SHORT).show();

                // Remove the item on remove/button click
                mExampleList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mExampleList.size());

            }
        });


    }



    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
