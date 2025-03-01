/*
 * @ (#) ThreadSafeInitialization.java       1.0     3/1/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.singleton;
/*
 * @author: Luong Tan Dat
 * @date: 3/1/2025
 */

public class ThreadSafeInitialization {
    private static ThreadSafeInitialization instance;

    private ThreadSafeInitialization() {
    }

    public static synchronized ThreadSafeInitialization getInstance() {
        if (instance == null) {
            instance = new ThreadSafeInitialization();
        }
        return instance;
    }
    public static ThreadSafeInitialization getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (ThreadSafeInitialization.class) {
                if (instance == null) {
                    instance = new ThreadSafeInitialization();
                }
            }
        }
        return instance;
    }
}
