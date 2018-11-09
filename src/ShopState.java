public abstract class ShopState {
    private ShopContext _shopContext;

    protected ShopState(ShopContext shopContext, Status shopStateStatus) {
        _shopContext = shopContext;

        if (_shopContext.getShop() != null)
            shopContext.getShop().setShopStateStatus(shopStateStatus);
    }

    public ShopContext getShopContext() {
        return _shopContext;
    }

    public void setShopContext(ShopContext shopContext) {
        _shopContext = shopContext;
    }

    public abstract ShopStateLoop.Status execute();

    protected boolean returnLater(String answer) {
        boolean returnLater = answer.toLowerCase().equals("later");

        if (returnLater) {
            System.out.println();
            System.out.println("*** RETURN LATER TO FINISH ***");
        }

        return returnLater;
    }

    public enum Status {
        Create,
        AddItems,
        ChoosePaymentMethod,
        PayCash,
        PayCheck,
        ShowReceipt
    }

}
