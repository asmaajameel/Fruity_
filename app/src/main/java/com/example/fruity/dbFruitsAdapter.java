package com.example.fruity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dbFruitsAdapter extends RecyclerView.Adapter<dbFruitsAdapter.TasksViewHolder> {

    interface fruitClickListner {
        public void fruitClicked(Fruit selectedCity);
    }
    private Context myFruittx;
    public List<Fruit> fruitList;
    fruitClickListner listner;
    public dbFruitsAdapter(Context myFruittx, List<Fruit> fruitList) {
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
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Fruit fruit = fruitList.get(getAdapterPosition());
            listner.fruitClicked(fruit);
        }
    }
}
