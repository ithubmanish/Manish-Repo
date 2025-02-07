package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String s = new String();
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        System.out.println("String: " + s);
        System.out.println("Reverse String: "+reverseString(s));


    }
    public static String reverseString(String str){
        String rev="";
        for(int i=str.length()-1;i>=0;i--){
            rev=rev + str.charAt(i);
        }
        return rev;
    }
}