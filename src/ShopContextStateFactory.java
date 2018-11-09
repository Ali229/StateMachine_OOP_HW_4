public class ShopContextStateFactory {
    public static ShopState get(ShopContext context) throws Exception {
        assert context != null;
        assert context.getShop() != null;

        ShopState.Status shopStateStatus = context.getShop().getShopStateStatus();

        switch (shopStateStatus) {
            case Create:
                return new ShopStateCreate(context);

            case AddItems:
                return new ShopStateAddItems(context);

            case ChoosePaymentMethod:
                return new ShopStateChoosePaymentMethod(context);

            case PayCash:
                return new ShopStateAcceptPaymentCash(context);

            case PayCheck:
                return new ShopStateAcceptPaymentCheck(context);

            case ShowReceipt:
                return new ShopStateShowReceipt(context);

            default:
                throw new Exception(shopStateStatus + " is an invalid state");
        }
    }
}
