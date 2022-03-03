package main;

import myObject.Menu;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();

        while(true) {

            menu.printMainMenu();

            if(menu.choose == 1) {
                menu.addCustomMenu();
            }
            else if(menu.choose == 2) {
                menu.updateCustomer();
            }
            else if(menu.choose == 3) {
                menu.deleteCustomMenu();
            }
            else if(menu.choose == 4) {
                menu.customerList();
            }
            else if(menu.choose == 5) {
                menu.closeInput();
                System.out.println("exited");
                return;
            }

        }

    }
}
