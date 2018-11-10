import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Scanner;

public class ShopStateAcceptPaymentCheck extends ShopState {
    public ShopStateAcceptPaymentCheck(ShopContext context) {
        super(context, Status.PayCheck);
    }

    private BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        List<String> _quantity = getShopContext().getShop().getQuantities();
        List<String> _prices = getShopContext().getShop().getPrices();
        for (int x = 0; x < _quantity.size(); x++) {
            double calc = Double.parseDouble(_quantity.get(x)) * Double.parseDouble(_prices.get(x));
            total = total.add(new BigDecimal(calc, MathContext.DECIMAL64));
        }
        return total;
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println("*** ACCEPT CHECK PAYMENT ***");
        System.out.println("Your total is $" + getTotal());
        Scanner input = new Scanner(System.in);
        System.out.println("Enter check amount");
        BigDecimal cashIn = input.nextBigDecimal();
        getShopContext().getShop().setPayment(new PaymentCash(cashIn));
        while (!(getShopContext().getShop().getPayment().getAmount().equals(getTotal()))) {
            System.out.println("Invalid check input, it should be exact amount!");
            cashIn = input.nextBigDecimal();
            getShopContext().getShop().setPayment(new PaymentCash(cashIn));
        }
        System.out.println("Enter check number");
        int checkNumber = input.nextInt();
        while (checkNumber < 101) {
            System.out.println("Check number must be greater than 100");
            checkNumber = input.nextInt();
        }
        System.out.println("Check number " + checkNumber + " of amount $" + cashIn + " is accepted.");
        getShopContext().changeState(new ShopStateShowReceipt(getShopContext()));
        return ShopStateLoop.Status.Continue;
    }
}