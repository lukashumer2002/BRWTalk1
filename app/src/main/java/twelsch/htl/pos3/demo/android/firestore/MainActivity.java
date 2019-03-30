package twelsch.htl.pos3.demo.android.firestore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    List<Note> list=new ArrayList<Note>();
    EditText message;
    Button senden;
    private FirebaseAuth mAuth;
    ListView listView;
    boolean login;
    String currentEmail;
    String collectionName;
    FirebaseFirestore db;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = false;
        collectionName = "Notes";
        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.listView);
        message = findViewById(R.id.editTextMessage);
        senden = findViewById(R.id.buttonSenden);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        senden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String test = "/login lukashumer@gmail.com testtest";
                String nachricht=message.getText().toString().trim();

                if(login==false){
                    addUser(nachricht);
                }
                else if(login){
                    write(nachricht);
                }

                message.setText(null);
            }

        });


        }

        public void addUser(String s)
        {
            if(s.contains("/signin"))
            {


                String[] arr = s.split(" ");
                //User user = new User(arr[1],arr[2]);
                final String email = arr[1];
                String password = arr[2];

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("<<<<<<<<<<<<<<<<<<", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    currentEmail=email;
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("<<<<<<<<<<<<<<<<<", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });


            login = true;
            }

            else if(s.contains("/login"))
            {
                String[] arr = s.split(" ");
                //User user = new User(arr[1],arr[2]);
                final String email = arr[1];
                String password = arr[2];
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(">>>>>>>>>>>>>>>>>>>>", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this,"FUNKT",Toast.LENGTH_LONG).show();
                                    currentEmail=email;
                                    //       updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(">>>>>>>>>>>>>>>>>>>>>>>>><", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //         updateUI(null);
                                }

                                // ...
                            }
                        });
            login=true;
            }
        }

        public void updateUI()
        {

        }

        public void write(String message)
        {

             final Note note123 = new Note("4711",currentEmail,message,LocalDateTime.now());

            //Note note = new Note("4711", "foo", true, true, new Date());
            // add note to firestore db
            // https://firebase.google.com/docs/firestore/manage-data/add-data
            db.collection(collectionName)
                    .document(String.valueOf(note123.getId()))
                    .set(note123)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            list.add(note123);
                            Log.d("firestoreDemo.set", "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("firestoreDemo.set", "Error writing document", e);
                        }
                    });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            adapter = new Adapter(this, (ArrayList<Note>) list);
            listView.setAdapter(adapter);
        }


        public void read()
        {
            db.collection(collectionName)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Note note = document.toObject(Note.class);

                                    list.add(note);

                                    Log.d("firestoreDemo.get", note.toString());
                                }
                            } else {
                                Log.w("firestoreDemo.get", "Error getting documents.", task.getException());
                            }
                        }
                    });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            adapter = new Adapter(this, (ArrayList<Note>) list);
            listView.setAdapter(adapter);
        }
}

