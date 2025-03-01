/*
 * @ (#) LazyInitialization.java       1.0     3/1/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.singleton;
/*
 * @author: Luong Tan Dat
 * @date: 3/1/2025
 */

public class LazyInitialization {
    private static LazyInitialization instance;

    private LazyInitialization() {
    }

    public static LazyInitialization getInstance() {
        if (instance == null) {
            instance = new LazyInitialization();
        }
        return instance;
    }
}
