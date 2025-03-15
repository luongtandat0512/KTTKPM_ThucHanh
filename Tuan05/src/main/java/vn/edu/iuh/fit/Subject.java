/*
 * @ (#) Subject.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit;

/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */
public interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notify(String message);
}
