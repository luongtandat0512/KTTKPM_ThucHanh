package vn.edu.iuh.fit;

import vn.edu.iuh.fit.singleton.*;

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
//        LazyInitialization singleton5 = LazyInitialization.getInstance();
//        System.out.println("Lazy Initialization 5 :" + singleton5.hashCode());
//        LazyInitialization singleton6 = LazyInitialization.getInstance();
//        System.out.println("Lazy Initialization 6 :" + singleton6.hashCode());
//
        // Thread Safe Initialization
//        new Thread(() -> {
//            ThreadSafeInitialization singleton7 = ThreadSafeInitialization.getInstance();
//            System.out.println("Thread Safe Initialization 7 :" + singleton7.hashCode());
//        }).start();
//        new Thread(() -> {
//            ThreadSafeInitialization singleton8 = ThreadSafeInitialization.getInstance();
//            System.out.println("Thread Safe Initialization 8 :" + singleton8.hashCode());
//        }).start();
//
//        ThreadSafeInitialization singleton9 = ThreadSafeInitialization.getInstanceUsingDoubleLocking();
//        System.out.println("Thread Safe Initialization Using Double Locking 9 :" + singleton9.hashCode());
//        ThreadSafeInitialization singleton10 = ThreadSafeInitialization.getInstanceUsingDoubleLocking();
//        System.out.println("Thread Safe Initialization Using Double Locking 10 :" + singleton10.hashCode());

        // Bill Pugh Initialization
//        BillPughInitialization singleton11 = BillPughInitialization.getInstance();
//        System.out.println("Bill Pugh Initialization 11 :" + singleton11.hashCode());
//        BillPughInitialization singleton12 = BillPughInitialization.getInstance();
//        System.out.println("Bill Pugh Initialization 12 :" + singleton12.hashCode());

        // Using Reflection to destroy Singleton Pattern
        EagerInitialization singleton = EagerInitialization.getInstance();
        EagerInitialization singletonTwo = null;

    }


}