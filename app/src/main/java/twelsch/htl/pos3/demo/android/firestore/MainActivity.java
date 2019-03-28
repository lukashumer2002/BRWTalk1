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
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    EditText message;
    Button senden;
    private FirebaseAuth mAuth;
    ListView listView;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = false;
        listView = findViewById(R.id.listView);
        message = findViewById(R.id.editTextMessage);
        senden = findViewById(R.id.buttonSenden);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);



                        senden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nachricht=message.getText().toString().trim();
                if(login==false){addUser(nachricht);}
                


                message.setText(null);
            }
        });


        }

        public void addUser(String s)
        {
            if(s.contains("/login"))
            {
                // /login lukashumer test

                String[] arr = s.split(" ");
                //User user = new User(arr[1],arr[2]);
                String email = arr[1];
                String password = arr[2];
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("<<<<<<<<<<<<<<<<<<", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
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
        }

        public void updateUI()
        {

        }
}

