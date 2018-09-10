package com.example.githubclient.util;

import java.util.Collection;

public class CollectionUtils {

    public static boolean notNullAndNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
}
