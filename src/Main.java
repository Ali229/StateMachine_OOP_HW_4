import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ShopStateLoop shopStateLoop = new ShopStateLoop();
        Shop shop;

        try {
            shop = shopStateLoop.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }


        while (true) {
            showCart(shop);
            System.out.println();
            System.out.println("Simulate shop reload to correct state? [yes]");

            if (scanner.nextLine().toLowerCase().trim().equals("yes")) {
                try {
                    shopStateLoop.execute(shop);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    break;
                }
            } else {
                break;
            }
        }
    }

    private static void showCart(Shop shop) {
        System.out.println("Order " + shop.getOrderId() + " is not complete - cannot produce order yet");
        System.out.println("Order " + shop.getOrderId() + " state = " + shop.getShopStateStatus());
        System.out.println();
    }
}
