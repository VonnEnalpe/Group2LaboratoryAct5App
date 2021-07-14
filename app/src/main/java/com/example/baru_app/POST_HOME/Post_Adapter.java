package com.example.baru_app.POST_HOME;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baru_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Post_Adapter extends FirestoreRecyclerAdapter<Post_Model, Post_Adapter.PostHolder> {

    public Post_Adapter(@NonNull FirestoreRecyclerOptions<Post_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostHolder holder, int position, @NonNull Post_Model model) {
    holder.Post_Title.setText(model.getTitle());
    holder.Post_Date.setText(model.getDate());
    holder.Post_Description.setText(model.getDescription());
    holder.Post_NumComment.setText(model.getNumberComment());
    holder.Post_Time.setText(model.getTime());
    holder.Post_Author.setText(model.getAuthor());
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,parent, false);
        return new PostHolder(view);
    }

    class PostHolder extends RecyclerView.ViewHolder{
        TextView Post_Title,Post_Date,Post_Description,Post_NumComment,Post_Time,Post_Author;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            Post_Author = itemView.findViewById(R.id.postAuthor);
            Post_Title = itemView.findViewById(R.id.postTitle);
            Post_Date = itemView.findViewById(R.id. postDate);
            Post_Time = itemView.findViewById(R.id. postTime);
            Post_Description = itemView.findViewById(R.id. postContent);
            Post_NumComment = itemView.findViewById(R.id. post_commentSize);
        }


    }
}
