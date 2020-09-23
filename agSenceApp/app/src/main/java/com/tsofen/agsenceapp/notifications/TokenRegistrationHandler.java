package com.tsofen.agsenceapp.notifications;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

/**
 * This class handles token registration in remote server, for example Firebase DB
 */
public class TokenRegistrationHandler {

    /**
     * Registration of token for specific username
     *
     * @param userName user's name
     * @param token    generated fire-base access token
     * @return indication weather registration has completed successfully
     */
    public static boolean registerToken(String userName, String token) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("token", token);
        db.collection("user2Token").document(userName).set(userData, SetOptions.merge());
        return true;
    }
}
