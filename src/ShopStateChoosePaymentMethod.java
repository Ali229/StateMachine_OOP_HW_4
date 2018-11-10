import java.util.Scanner;

public class ShopStateChoosePaymentMethod extends ShopState {

    public ShopStateChoosePaymentMethod(ShopContext context) {
        super(context, Status.ChoosePaymentMethod);
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println("*** CHOOSE PAYMENT METHOD ***");
        System.out.println();
        System.out.println("- COMMAND: [later] to return later, [cash] or [check]");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String paymentType = new String();

            if (scanner.hasNext())
                paymentType = scanner.next().trim();

            if (returnLater(paymentType)) return ShopStateLoop.Status.Stop; //exit loop and method

            //empty entry does nothing
            if (paymentType.isEmpty()) continue;

            if (paymentType.equals("cash")) {
                getShopContext().changeState(new ShopStateAcceptPaymentCash(getShopContext()));
                return ShopStateLoop.Status.Continue;
            }

            if (paymentType.equals("check")) {
                getShopContext().changeState(new ShopStateAcceptPaymentCheck(getShopContext()));
                return ShopStateLoop.Status.Continue;
            }

            System.out.println("- ERROR: [later], [cash], or [check]");
        }
    }
}
