public class ShopStateShowReceipt extends ShopState {
    public ShopStateShowReceipt(ShopContext context) {
        super(context, Status.ShowReceipt);
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println("*** YOUR RECEIPT ***");
        return ShopStateLoop.Status.Stop;
    }
}