package ithub.iinventory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akila Devinda on 1/31/2018.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExmapleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mItemName;
        public TextView mItemPrice;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.itemName);
            mItemPrice = itemView.findViewById(R.id.itemPrice);

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
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        ExmapleItem currentItem = mExampleList.get(position);

        holder.mItemName.setText(currentItem.getItemName());
        holder.mItemPrice.setText(currentItem.getItemPrice());



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
