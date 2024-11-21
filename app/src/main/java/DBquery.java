
    package com.quizzes.mytutor;

import static androidx.appcompat.widget.ResourceManagerInternal.get;

import android.net.wifi.WifiManager;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

    public class DbQuery {
        public static FirebaseFirestore g_firestore;
        public static List<CategoryModel> g_catList= new ArrayList<>();
        public static int g_selected_cat_index = 0;
        public static List<TestModel> g_test_List = new ArrayList<>();
        public static List<QuestionModel> g_qestlist = new ArrayList<>();
        public static int g_selected_test_index = 0;
        public static ProfileModel myProfile = new ProfileModel (name "NA", email: null);
        public static void createusetData(String email, String name, final  MycompleteListener complete listener) {
        }
        public static void loadquestions(MyCompleteListener,completeListener){
            g_qestlist.clear();
         g_firestore.collection(collectionPath: "Questions")
            .whereEqualTo(field: "CATEGORY", g_catList.get(g_selected_cat_index).getDocID())
            .whereEqualTo(field: "TEST", g_test_List.get(g_selected_test_index).getTestID())
            .get()
                    .addOnSuccessListener(new OnSuccessListener <QuerySnapshots) {
                     @Override
                             public  void onSuccess(QuerySnapshot QueryDocumentSnapshots){
                             for(DocumentSnapshot doc: QueryDocumentSnapshots) {
                                 g_qestlist.add(new QuestionModel){
                               doc.getString(field: "QUESTION"),
                                 doc.getString(field: "A"),
                                 doc.getString(field: "C"),
                                 doc.getString(field:"D"),
                                 doc.getLong(field:"ANSWER").initValue()
                             ));



                     }
                             completeListener.onSuccess();
                     }
                })
                    .addOnFailureListener(new OnFailureListener(){
                        @Override
                    public void  onFailure(@NonNull Exception e){
                            CompleteListener.failure();
                        }
                    });




        }

        public static void createUserData(String email, String name, MyCompleteListener completeListener) {
            Map<String, Object> userData = new HashMap<>();

            userData.put("EMAIL_ID", email);
            userData.put("NAME", name);
            userData.put("TOTAL_SCORE", 0);

            DocumentReference userDoc = g_firestore.collection("USERS")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid());

            WriteBatch batch = g_firestore.batch();
            batch.set(userDoc, userData);

            DocumentReference countDoc = g_firestore.collection("USERS").document("TOTAL USERS");
            batch.update(countDoc, "COUNT", FieldValue.increment(1));

            batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    completeListener.onSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    completeListener.onFailure();
                }
            });

        }
    }
}
