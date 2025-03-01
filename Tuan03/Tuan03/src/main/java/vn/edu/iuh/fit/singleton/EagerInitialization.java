/*
 * @ (#) Eager.java       1.0     3/1/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.singleton;
/*
 * @author: Luong Tan Dat
 * @date: 3/1/2025
 */

public class EagerInitialization {
    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {
    }

    public static EagerInitialization getInstance() {
        return instance;
    }
}
