package com.company;

import java.util.Scanner;

class Menu {
    Scanner scanner = new Scanner(System.in);
    int menu() {
        System.out.println("------------------------------------");
        System.out.println(" 1. buscar(filter)");
        System.out.println(" 2. quantitat (count)");
        System.out.println(" 3. Max Any");
        System.out.println(" 4. Min Any");
        System.out.println(" 5. Monstra Any(distinct)");
        System.out.println(" 6. Monstra Any Ordenat(sorted)");
        System.out.println(" 7. Map");
        System.out.println("------------------------------------");
        return scanner.nextInt();
    }
    int camps_buscar(){
        System.out.println("------------------------------------");
        System.out.println(" 1. ID");
        System.out.println(" 2. TITOL");
        System.out.println("------------------------------------");
        return scanner.nextInt();
    }
    int quantitat_buscar(){
        System.out.println("------------------------------------");
        System.out.println(" 1. DIRECCIO");
        System.out.println(" 2. Any");
        System.out.println("------------------------------------");
        return scanner.nextInt();
    }

}
