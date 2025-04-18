/*
 * @ (#) TeamMember.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.TaskExample;
/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */

import vn.edu.iuh.fit.ComposePattern.Observer;

public class TeamMember implements Observer {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
