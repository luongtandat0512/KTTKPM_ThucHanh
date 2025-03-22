/*
 * @ (#) File.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.FileExample;
/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */

import vn.edu.iuh.fit.ComposePattern.Observer;

public class File implements Observer {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void update(String message) {
        System.out.println("File " + name + " (Size: " + size + ")");
    }
}
