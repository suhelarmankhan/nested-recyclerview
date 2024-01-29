package com.tecoditlimited.nestedrecyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecoditlimited.nestedrecyclerview.Model.ChildModelClass;
import com.tecoditlimited.nestedrecyclerview.R;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {


    List<ChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter(List<ChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout, null,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {

        // get Adapter Position
        int clickedPosition = holder.getAdapterPosition();

        holder.iv_child_image.setImageResource(childModelClassList.get(position).image);

        //item on click
        holder.iv_child_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast
                Toast.makeText(context.getApplicationContext(), "Item clicked " + clickedPosition , Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_child_image = itemView.findViewById(R.id.iv_child_item);

        }
    }
}
