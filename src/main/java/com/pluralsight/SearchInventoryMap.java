package com.pluralsight;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class SearchInventoryMap {

        // the key is the product id, the value is a product object
        public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();
        public static void main (String[]args){
// this method loads product objects into inventory
            loadInventory();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("What item # are you interested in? ");
                int id = scanner.nextInt();
                Product matchedProduct = inventory.get(id);
                if (matchedProduct == null) {
                    System.out.println("We don't carry that product");
                    return;
                }
                System.out.printf("We carry %s and the price is $%.2f. ",
                        matchedProduct.getName(), matchedProduct.getPrice());

                System.out.println("\n Would you like to view another product? (Y/N)");
                String userResponse = scanner.next();
                if (userResponse.equalsIgnoreCase("n")) {
                    break;
                }


            }
        }

    private static void loadInventory() {
        try {
            BufferedReader bufRead = new BufferedReader( new FileReader("src/main/resources/Inventory.csv"));
            String input;
            while((input = bufRead.readLine()) != null) {
                String [] categories = input.split("\\|");
                int productId = Integer.parseInt(categories[0]);
                String productName = categories[1];
                float productPrice = Float.parseFloat(categories[2]);
                inventory.put(productId, new Product(productId, productName, productPrice));
            }

        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}