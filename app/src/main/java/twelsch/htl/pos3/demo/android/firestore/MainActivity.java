package twelsch.htl.pos3.demo.android.firestore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init note
        String collectionName = "Notes";
        Note note = new Note("4711", "foo", true, true, new Date());

        // init firestore db
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // add note to firestore db
        // https://firebase.google.com/docs/firestore/manage-data/add-data
        db.collection(collectionName)
                .document(String.valueOf(note.getId()))
                .set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
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

        // update note message in firestore
        // https://firebase.google.com/docs/firestore/manage-data/add-data
        db.collection(collectionName)
                .document(note.getId())
                .update("message", "bar")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("firestoreDemo.update", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firestoreDemo.update", "Error updating document", e);
                    }
                });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // read all data from firestore db
        // https://firebase.google.com/docs/firestore/query-data/get-data
        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Note note = document.toObject(Note.class);

                                TextView tv = findViewById(R.id.textView);
                                tv.setText(note.toString());

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

        // delete note from firestore db
        // https://firebase.google.com/docs/firestore/manage-data/delete-data
//        db.collection(collectionName).document(note.getId())
//                .delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("firestoreDemo.delete", "DocumentSnapshot successfully deleted!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("firestoreDemo.delete", "Error deleting document", e);
//                    }
//                });
    }
}
