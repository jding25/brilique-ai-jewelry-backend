package com.aijewelry.model;

public class DesignKey {
    private DesignKey() {}

    public static String encode(String designUserId, String designId) {
        return designUserId + "#" + designId;
    }

    public static String[] decode(String key) {
        int i = key.indexOf('#');
        if (i == -1) {
            throw new IllegalArgumentException("Invalid design key format: " + key);
        }
        return new String[]{ key.substring(0, i), key.substring(i + 1) };

    }

}
