/*
 * @ (#) BillPughInitialization.java       1.0     3/1/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.singleton;
/*
 * @author: Luong Tan Dat
 * @date: 3/1/2025
 */

public class BillPughInitialization {
    private BillPughInitialization() {
    }

    private static class SingletonHelper {
        private static final BillPughInitialization INSTANCE = new BillPughInitialization();
    }

    public static BillPughInitialization getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
