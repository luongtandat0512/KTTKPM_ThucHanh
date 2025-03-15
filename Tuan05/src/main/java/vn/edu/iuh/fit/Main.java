package vn.edu.iuh.fit;

public class Main {
    public static void main(String[] args) {
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


    }
}