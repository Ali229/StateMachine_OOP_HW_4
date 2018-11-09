public class ShopContextStateFactory {
    public static ShopState get(ShopContext context) throws Exception {
        assert context != null;
        assert context.getShop() != null;

        ShopState.Status tripStateStatus = context.getShop().getShopStateStatus();

        switch (tripStateStatus) {
            case Create:
                return new ShopStateCreate(context);

            case AddItems:
                return new ShopStateAddItems(context);

            case ChoosePaymentMethod:
                return new ShopStateChoosePaymentMethod(context);
//
//            case AddThankYou:
//                return new ShopStateAddThankYou(context);
//
//            case Complete:
//                return new ShopStateComplete(context);

            default:
                throw new Exception(tripStateStatus + " is an invalid state");
        }
    }
}
