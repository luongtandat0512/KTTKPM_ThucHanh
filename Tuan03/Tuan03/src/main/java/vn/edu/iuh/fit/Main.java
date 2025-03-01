package vn.edu.iuh.fit;

import vn.edu.iuh.fit.singleton.EagerInitialization;
import vn.edu.iuh.fit.singleton.LazyInitialization;
import vn.edu.iuh.fit.singleton.StaticBlockInitialization;

public class Main {
    public static void main(String[] args) {
        // Eager Initialization
//        EagerInitialization singleton = EagerInitialization.getInstance();
//        System.out.println("Eager Initialization 1 :" + singleton.hashCode());
//        EagerInitialization singleton2 = EagerInitialization.getInstance();
//        System.out.println("Eager Initialization 2 :" + singleton2.hashCode());

        // Static Block Initialization
//        StaticBlockInitialization singleton3 = StaticBlockInitialization.getInstance();
//        System.out.println("Static Block Initialization 3 :" + singleton3.hashCode());
//        StaticBlockInitialization singleton4 = StaticBlockInitialization.getInstance();
//        System.out.println("Static Block Initialization 4 :" + singleton3.hashCode());

        // Lazy Initialization
        LazyInitialization singleton5 = LazyInitialization.getInstance();
        System.out.println("Lazy Initialization 5 :" + singleton5.hashCode());
        LazyInitialization singleton6 = LazyInitialization.getInstance();
        System.out.println("Lazy Initialization 6 :" + singleton6.hashCode());
    }

}