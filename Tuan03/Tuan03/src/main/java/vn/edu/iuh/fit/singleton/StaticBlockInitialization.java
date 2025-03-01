/*
 * @ (#) StaticBlockInitialization.java       1.0     3/1/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.singleton;
/*
 * @author: Luong Tan Dat
 * @date: 3/1/2025
 */

public class StaticBlockInitialization {
    private static final StaticBlockInitialization instance;

    private StaticBlockInitialization() {
    }

    static {
        try {
            instance = new StaticBlockInitialization();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockInitialization getInstance() {
        return instance;
    }
}
