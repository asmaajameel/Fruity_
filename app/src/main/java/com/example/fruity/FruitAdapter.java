package com.example.fruity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.TasksViewHolder> {

    interface fruitClickListner {
        public void fruitClicked(Fruit selectedCity);
    }
    private Context myFruittx;
    public List<Fruit> fruitList;
    //public ArrayList<FruitData> ff;
    fruitClickListner listner;
    public FruitAdapter(Context myFruittx, List<Fruit> fruitList) {
        this.myFruittx = myFruittx;
        this.fruitList = fruitList;
        listner = (fruitClickListner)myFruittx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myFruittx).inflate(R.layout.recyclerview_fruits, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Fruit t = fruitList.get(position);
        //FruitData f = ff.get(position);
        holder.fruitTextView.setText(t.getFruitName() );
        Glide.with(myFruittx)
                .load(t.imageurl)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fruitTextView;//
        ImageView img;
        public TasksViewHolder(View itemView) {
            super(itemView);
            fruitTextView = itemView.findViewById(R.id.fruity);
            img = itemView.findViewById(R.id.imagee);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Fruit fruit = fruitList.get(getAdapterPosition());
            listner.fruitClicked(fruit);
        }
    }
}
