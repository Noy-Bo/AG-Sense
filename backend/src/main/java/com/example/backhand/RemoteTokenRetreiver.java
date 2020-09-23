package com.example.backhand;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.concurrent.ExecutionException;

public class RemoteTokenRetreiver {

    private Firestore db;

    public RemoteTokenRetreiver() {
        FireBaseAppInitializer.getAppInstance();
        db = FirestoreClient.getFirestore();
    }

    /**
     * Getting user's token from fire-base DB
     *
     * @param userName user name
     * @return access token
     */
    public String getTokenForUser(String userName) {
        ApiFuture<DocumentSnapshot> future = db.collection("user2Token").document(userName).get();
        try {
            return future.get().getString("token");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "uknown";
    }
}
