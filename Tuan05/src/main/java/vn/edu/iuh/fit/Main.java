package vn.edu.iuh.fit;

import vn.edu.iuh.fit.FileExample.Directory;
import vn.edu.iuh.fit.FileExample.File;

public class Main {
    public static void main(String[] args) {
        // Observer Pattern Example
        Directory directory = new Directory("Documents");
        Directory directory1 = new Directory("Downloads");
        File file = new File("1", 100);
        File file1 = new File("2", 200);
        File file2 = new File("3", 300);

        directory.register(file);
        directory.register(file1);
        directory1.register(file2);

        directory.notify(null);
        directory1.notify(null);

        /*
        // Observer Pattern Example
        Stock stock = new Stock("Google", 1000);
        Stock stock1 = new Stock("Apple", 2000);
        Investor investor1 = new Investor("A");
        Investor investor2 = new Investor("B");
        Investor investor3 = new Investor("C");

        stock.register(investor1);
        stock1.register(investor2);
        stock1.register(investor3);

        stock.setPrice(1200);

        stock1.setPrice(500);

        // Task Management Example
        Task task = new Task("Task 1", "Prepare");
        Task task1 = new Task("Task 2", "Implement");

        TeamMember teamMember1 = new TeamMember("A");
        TeamMember teamMember2 = new TeamMember("B");
        TeamMember teamMember3 = new TeamMember("C");

        task.register(teamMember1);
        task.register(teamMember2);
        task1.register(teamMember3);

        task.setStatus("Implement");

        task1.setStatus("Test");

*/
    }
}