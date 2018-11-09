import java.math.BigDecimal;
import java.util.List;

public class ShopStateAcceptPaymentCheck extends ShopState {
    public ShopStateAcceptPaymentCheck(ShopContext context) {
        super(context, Status.PayCheck);
    }

    private Double getTotal() {
        double total = 0.0;
        List<String> _quantity = getShopContext().getShop().getQuantities();
        List<String> _prices = getShopContext().getShop().getPrices();
        for (int x = 0; x < _quantity.size(); x++) {
            total += Double.parseDouble(_quantity.get(x)) * Double.parseDouble(_prices.get(x));
        }
        return total;
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println("Your total is $" + getTotal());
        System.out.println(System.lineSeparator());
        System.out.println("*** ACCEPT CASH PAYMENT ***");
        System.out.println();
        System.out.println("- COMMAND: [later] to return later or amount");
        System.out.println();

        getShopContext().getShop().setPayment(new PaymentCash(BigDecimal.valueOf(10000)));
        System.out.println("Paid $10000 cash");

        getShopContext().changeState(new ShopStateAcceptPaymentCheck(getShopContext()));
        return ShopStateLoop.Status.Continue;
    }
}