import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Scanner;

public class ShopStateAcceptPaymentCash extends ShopState {
    public ShopStateAcceptPaymentCash(ShopContext context) {
        super(context, Status.PayCash);
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
        System.out.println("*** ACCEPT CASH PAYMENT ***");
        System.out.println("Your total is $" + getTotal());
        Scanner input = new Scanner(System.in);
        System.out.println("Enter amount");
        String cashIn = input.nextLine().trim();
        getShopContext().getShop().setPayment(new PaymentCash(BigDecimal.valueOf(Double.parseDouble(cashIn))));
        while (!getShopContext().getShop().getPayment().getAmount().equals(new BigDecimal(getTotal(), MathContext.DECIMAL64))) {
            System.out.println("Invalid cash input, input cash again!");
            cashIn = input.nextLine().trim();
            getShopContext().getShop().setPayment(new PaymentCash(BigDecimal.valueOf(Double.parseDouble(cashIn))));
        }

        getShopContext().changeState(new ShopStateShowReceipt(getShopContext()));
        return ShopStateLoop.Status.Continue;
    }
}