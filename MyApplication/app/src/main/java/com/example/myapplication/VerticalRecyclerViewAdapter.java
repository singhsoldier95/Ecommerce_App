package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.HomeVerticalModel;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.ViewHolder> {
    private ArrayList<HomeVerticalModel> categoryInfo;
    private Context mContext;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<HomeVerticalModel> categoryInfo) {
        mContext = context;
        this.categoryInfo = categoryInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_products, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HomeVerticalModel homePageViewModel = categoryInfo.get(position);
        String title = homePageViewModel.getTitle();
        ArrayList<Product> singleProduct = homePageViewModel.getProducts();

        holder.title.setText(title);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(mContext, singleProduct);
        holder.categoryRecyclerView.setHasFixedSize(true);

        holder.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.categoryRecyclerView.setAdapter(horizontalRecyclerViewAdapter);

        holder.h_categories_parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + categoryInfo.get(position).getTitle());
                Toast.makeText(mContext, categoryInfo.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView categoryRecyclerView;
        LinearLayout h_categories_parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryRecyclerView = itemView.findViewById(R.id.h_categories_child_layout);
            h_categories_parent_layout = itemView.findViewById(R.id.h_categories_parent_layout);
            title = itemView.findViewById(R.id.category_title);
        }
    }
}
