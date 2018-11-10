import java.util.Scanner;

public class ShopStateAddItems extends ShopState {

    public ShopStateAddItems(ShopContext context) {
        super(context, Status.AddItems);
    }

    private boolean isItemListValid() {
        if (!getShopContext().getShop().getItems().isEmpty())
            return true;

        System.out.println("ERROR: At least ONE item is required.");
        return false;
    }

    private boolean isItemValid(String newItem) {
        if (newItem.isEmpty()) {
            System.out.println("ERROR: Blank items are prohibited!");
            return false;
        }
        if (newItem.length() < 5) {
            System.out.println("ERROR: Item name must be 5 characters!");
            return false;
        }
        return true;
    }

    private boolean continueEnteringItems(String newItems) {
        boolean done = newItems.toLowerCase().equals("done");

        if (done && !getShopContext().getShop().getItems().isEmpty()) {
            System.out.println("*** Items: " + getShopContext().getShop().getItems().size() + " entered ***");
        }

        return !done;
    }

    private void showCurrentItems() {
        if (!getShopContext().getShop().getItems().isEmpty()) {

            System.out.println("- Currently " + getShopContext().getShop().getItems().size() + " in cart");

            for (int dest = 0; dest < getShopContext().getShop().getItems().size(); dest++)
                System.out.println((dest + 1) + ". " + getShopContext().getShop().getItems().get(dest));

            System.out.println();
        }
    }

    @Override
    public ShopStateLoop.Status execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("*** ADD ITEMS ***");
        System.out.println();
        showCurrentItems();
        System.out.println(
                "- COMMAND: [later] to return later, [done] to finish adding items, or enter item");

        Boolean getItems = true;
        while (getItems) {
            String newItem = scanner.nextLine().trim();
            if (returnLater(newItem)) return ShopStateLoop.Status.Stop;
            if (continueEnteringItems(newItem)) {
                if (isItemValid(newItem)) {
                    getShopContext().getShop().getItems().add(newItem);

                    System.out.println("Enter Quantity");
                    String quan = scanner.nextLine().trim();
                    while (quan.isEmpty() || Double.parseDouble(quan) < 1) {
                        System.out.println("Quantity should be 1 or more\nEnter Quantity");
                        quan = scanner.nextLine().trim();
                    }
                    getShopContext().getShop().getQuantities().add(quan);

                    System.out.println("Enter Price");
                    String price = scanner.nextLine().trim();
                    while (price.isEmpty() || !(Double.parseDouble(price) > 0)) {
                        System.out.println("Price should be greater than 0\nEnter Price");
                        price = scanner.nextLine().trim();
                    }
                    getShopContext().getShop().getPrices().add(price);

                    System.out.println("- Added item [" + newItem + "] - Enter more or enter done");
                }

            } else {
                getItems = !isItemListValid();
            }
        }

        getShopContext().changeState(new ShopStateChoosePaymentMethod(getShopContext()));
        return ShopStateLoop.Status.Continue;
    }
}
