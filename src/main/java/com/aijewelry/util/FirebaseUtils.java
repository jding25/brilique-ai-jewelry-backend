package com.aijewelry.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
public class FirebaseUtils {
    public static String verifyTokenAndGetUserId(String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(token);
            return decoded.getUid();
        } catch (Exception e) {
            throw new RuntimeException("Invalid Firebase token", e);
        }
    }
}
