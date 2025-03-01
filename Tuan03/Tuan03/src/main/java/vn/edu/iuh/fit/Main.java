package vn.edu.iuh.fit;

import vn.edu.iuh.fit.singleton.EagerInitialization;

public class Main {
    public static void main(String[] args) {
        // Eager Initialization
        EagerInitialization singleton = EagerInitialization.getInstance();
        System.out.println(singleton.hashCode());
        EagerInitialization singleton2 = EagerInitialization.getInstance();
        System.out.println(singleton2.hashCode());


    }

}