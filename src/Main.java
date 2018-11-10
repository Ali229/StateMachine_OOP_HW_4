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
        while (!(shop.getShopStateStatus().equals(ShopState.Status.ShowReceipt))) {
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

        if (shop.getShopStateStatus().equals(ShopState.Status.ShowReceipt)) {
            System.out.println("Would you like to make another purchase?");
            System.out.println("- COMMAND: [yes] to make another purchase, [no] to exit");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                String[] repeat = new String[0];
                main(repeat);
            }
        }
    }

    private static void showCart(Shop shop) {
        System.out.println("Order " + shop.getOrderId() + " state = " + shop.getShopStateStatus());
    }
}
