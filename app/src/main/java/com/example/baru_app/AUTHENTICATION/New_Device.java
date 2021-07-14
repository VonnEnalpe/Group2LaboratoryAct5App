package com.example.baru_app.AUTHENTICATION;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baru_app.DATABASE_SQL.BarangayUserModel;
import com.example.baru_app.DATABASE_SQL.DatabaseHelper;
import com.example.baru_app.R;
import com.example.baru_app.Services;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

public class New_Device extends AppCompatActivity {
    Spinner spinner;
    Button send_verification;
    String barangay_selected,sql_return_barangay,userID;
    DatabaseHelper databasehelper;
    DocumentReference documentReference;
    CollectionReference verify_Reference;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestoreDB;
    FirebaseStorage firebaseStorage;
    BarangayUserModel newUser;
    DatabaseHelper databaseHelper;
    Dialog loading_layout;
    boolean checker_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);

        send_verification = findViewById(R.id.send_verification);
        /// SPINNER
        spinner = (Spinner) findViewById(R.id.brgy_spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.barangay_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        /// FIREBASE
        firebaseAuth = FirebaseAuth.getInstance();
        firestoreDB = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        /// SQL
        databaseHelper = new DatabaseHelper(New_Device.this);


        //DIALOG LOADING LAYOUT
            loading_layout = new Dialog(this);
            loading_layout.setContentView(R.layout.loading_layout);
            loading_layout.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loading_layout.setCanceledOnTouchOutside(false);
            loading_layout.setCancelable(false);



        send_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //GET BARANGAY SPINNER
                barangay_selected = spinner.getSelectedItem().toString();
                String[] split_brgy = barangay_selected.split(" ");
                if(split_brgy.length == 1){
                    barangay_selected = split_brgy[0].toLowerCase();
                }else if(split_brgy.length == 2){
                    barangay_selected = split_brgy[0].toLowerCase()+split_brgy[1];
                }else{
                    barangay_selected = split_brgy[0].toLowerCase()+split_brgy[1]+split_brgy[2];
                }
                //
                try{
                    verify_Reference = firestoreDB.collection("barangays").document(barangay_selected).collection("users");

                    verify_Reference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            // FOR EACH
                            for (final QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                if (userID.equals(documentSnapshot.getId())) {
                                    newUser = new BarangayUserModel(-1,userID, barangay_selected);
                                    boolean success = databaseHelper.addOneUser(newUser);
                                    databaseHelper.close();
                                    checker_user = true;
                                    Intent intent = new Intent(New_Device.this, Services.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                                checker_user = false;
                            }
                            if (checker_user == false) {
                                Toast.makeText(New_Device.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });//END FAIL-SUCCESS LISTENER

                }catch (Exception e){
                   //
                }










            }
        });





    }
}