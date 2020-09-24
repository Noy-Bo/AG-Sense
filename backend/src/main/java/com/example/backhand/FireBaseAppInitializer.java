package com.example.backhand;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class FireBaseAppInitializer {
    private static FirebaseApp app;
    private static FileInputStream serviceAccount;

    public static FirebaseApp getAppInstance(){
        if (app == null)
        {
            try {
                serviceAccount = new FileInputStream("src/main/java/com/example/backhand/agsenceapp-f81e0-firebase-adminsdk-450eo-9159a7bbda.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://agsenceapp-f81e0.firebaseio.com")
                        .build();
                app = FirebaseApp.initializeApp(options);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return app;
    }
}
