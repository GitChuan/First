package day01;

import java.util.ArrayList;
import java.util.Scanner;

public class HelloWorld{
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("---------- Welcome to Student Management System -----------");
            System.out.println("1: Append Student");
            System.out.println("2: Delete Student");
            System.out.println("3: Modify Student");
            System.out.println("4: Search Student");
            System.out.println("5: Quit");
            System.out.print("Entry your chose:>");
            int num = sc.nextInt();

            switch (num){
                case 1-> append(list);
                case 2-> delete(list);
                case 3-> modify(list);
                case 4-> search(list);
                case 5-> {
                    System.out.println("Thanks for your using");
                    break;
                }
            }
        }


    }

    public static void append(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.print("How many student do you want to add:> ");
        int size = sc.nextInt();
        while((size--) != 0) {
            System.out.print("ID:> ");
            int id = sc.nextInt();
            if(isSameId(list, id)){
                continue;
            }
            if(id < 0){
                System.out.println("ID must be positive integer");
                continue;
            }

            System.out.print("Name:> ");
            String buff1 = sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Age:> ");
            int age = sc.nextInt();
            System.out.print("Address:> ");
            String buff2 = sc.nextLine();
            String address = sc.nextLine();
            Student s = new Student(id, name, age, address);
            list.add(s);
            System.out.printf("Add Successfully, %d students remaining\n", size);
        }
    }

    public static boolean isSameId(ArrayList<Student> list, int id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id){
                System.out.println("Same ID found, try again");
                return true;
            }
        }

        return false;
    }

    public static void delete(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.print("Entry your id:> ");
        int id = sc.nextInt();
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id){
                list.remove(i);
                flag = false;
                System.out.println("Delete Successfully");
                return;
            }
        }

        if(flag){
            System.out.println("Couldn't find");
            return;
        }
    }

    public static void modify(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.print("Entry your id:> ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id){
                flag = false;

                System.out.print("new name:> ");
                String name = sc.nextLine();
                list.get(i).setName(name);

                System.out.print("new age:> ");
                int age = sc.nextInt();
                sc.nextLine();
                list.get(i).setAge(age);

                System.out.print("new address:> ");
                String address = sc.nextLine();
                list.get(i).setAddress(address);

                System.out.println("Modify Successfully");
            }
        }

        if(flag){
            System.out.println("Couldn't find");
            return;
        }
    }

    public static void search(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.print("Entry student's ID:> ");
        int id = sc.nextInt();
        boolean flag = true;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id){
                flag = false;

                System.out.printf("ID: %d\n", list.get(i).getId());
                System.out.printf("Name: %s\n", list.get(i).getName());
                System.out.printf("Age: %d\n", list.get(i).getAge());
                System.out.printf("Address: %s\n", list.get(i).getAddress());
            }
        }

        if(flag){
            System.out.println("Couldn't find");
        }
    }
}