package com.example.baru_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baru_app.POST_HOME.Post_Adapter;
import com.example.baru_app.POST_HOME.Post_Model;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomePage extends AppCompatActivity {
    FirebaseFirestore firestoreDB;
    com.example.baru_app.POST_HOME.Post_Adapter Post_Adapter;
    CollectionReference PostListRef;
    FirebaseAuth firebaseAuth;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        firestoreDB = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        PostListRef = firestoreDB.collection("Post");



        post_recycleView();



    }
    public void GoTo_Services(View view){
        startActivity(new Intent(getApplicationContext(),Services.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }
    public void GoTo_Profile(View view){
        startActivity(new Intent(getApplicationContext(),Profile.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

    }
    public void AddPost(View view){
        Toast.makeText(this, "New Post Dialog", Toast.LENGTH_SHORT).show();

    }
    public void post_recycleView(){
        Query query = PostListRef.orderBy("Date",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Post_Model> Post_Options = new FirestoreRecyclerOptions.Builder<Post_Model>()
                .setQuery(query, Post_Model.class)
                .build();
        Post_Adapter = new Post_Adapter(Post_Options);
        RecyclerView post_View = findViewById(R.id.home_post_recycleView);
        post_View.setHasFixedSize(true);
        post_View.setLayoutManager(new LinearLayoutManager(this));
        post_View.setAdapter(Post_Adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Post_Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Post_Adapter.stopListening();
    }
}