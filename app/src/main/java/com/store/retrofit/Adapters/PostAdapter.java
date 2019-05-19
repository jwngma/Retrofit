package com.store.retrofit.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.store.retrofit.Model.Post;
import com.store.retrofit.R;

import java.util.List;

public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vie= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_item,viewGroup,false);

        return new PostViewHolder(vie);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {

        postViewHolder.tvvv.setText(postList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public  static  class PostViewHolder extends RecyclerView.ViewHolder{

        TextView tvvv;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvvv=itemView.findViewById(R.id.titleee);
        }
    }
}
