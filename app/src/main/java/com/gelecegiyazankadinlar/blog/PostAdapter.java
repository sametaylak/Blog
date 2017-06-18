package com.gelecegiyazankadinlar.blog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;

    PostAdapter(Context ctx, List<Post> postList) {
        this.context = ctx;
        this.postList = postList;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.itemTitle.setText(post.getTitle());
        holder.itemDescription.setText(post.getDescription());

        Log.d("IMAGE_URL", "onBindViewHolder: " + post.getImageUrl());

        Picasso.with(context)
                .load(post.getImageUrl())
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title) TextView itemTitle;
        @BindView(R.id.item_description) TextView itemDescription;
        @BindView(R.id.item_image) ImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
