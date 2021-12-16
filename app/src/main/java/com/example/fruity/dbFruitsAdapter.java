package com.example.fruity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class dbFruitsAdapter extends RecyclerView.Adapter<dbFruitsAdapter.TasksViewHolder> {

    interface fruitClickListner {
        public void fruitClickedListener(Fruit selectedFruit);
    }
    private Context myFruittx;
    public List<Fruit> fruitList=new ArrayList<>(0);
    fruitClickListner listner;
    public dbFruitsAdapter(Context myFruittx, List<Fruit> fruitList) {
        this.myFruittx = myFruittx;
        this.fruitList = fruitList;
        listner = (fruitClickListner)myFruittx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myFruittx).inflate(R.layout.recycleview_fav_row, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Fruit t = fruitList.get(position);
        holder.fruitTextView.setText(t.getFruitName() );
    }
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fruitTextView;//
        public TasksViewHolder(View itemView) {
            super(itemView);
            fruitTextView = itemView.findViewById(R.id.fruity);
            fruitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fruit fruit = fruitList.get(getAdapterPosition());
           listner.fruitClickedListener(fruit);
                }
            });
           // itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
//            Fruit fruit = fruitList.get(getAdapterPosition());
//            listner.fruitClickedListener(fruit);
        }
    }
}
