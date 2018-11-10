import java.util.List;

public class ShopStateShowReceipt extends ShopState {
    public ShopStateShowReceipt(ShopContext context) {
        super(context, Status.ShowReceipt);
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println("*** YOUR RECEIPT ***");
        System.out.format("%-20s%-20s%-20s%-20s\n", "Item", "Quantity", "Price", "Amount");
        System.out.println("------------------------------------------------------------------");
        List<String> items = getShopContext().getShop().getItems();
        List<String> quantities = getShopContext().getShop().getQuantities();
        List<String> prices = getShopContext().getShop().getPrices();
        for (int x = 0; x < getShopContext().getShop().getItems().size(); x++) {
            System.out.format("%-20s%-20s%-20s%-20s\n",
                    items.get(x),
                    quantities.get(x),
                    "$" + prices.get(x),
                    "$" + Double.parseDouble(quantities.get(x)) * Double.parseDouble(prices.get(x)));
        }
        System.out.println("------------------------------------------------------------------");
        System.out.format("%-20s%-20s%-20s%-20s\n", "", "", "Total", "$" + getShopContext().getShop().getPayment().getAmount());
        return ShopStateLoop.Status.Stop;
    }
}