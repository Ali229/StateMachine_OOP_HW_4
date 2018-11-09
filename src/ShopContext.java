public class ShopContext {

    private Shop _shop;
    private ShopState _shopState;

    public ShopContext() {
        _shopState = new ShopStateCreate(this);
    }

    public ShopContext(Shop shop) throws Exception {
        assert shop != null : "shop cannot be null";

        _shop = shop;
        _shopState = ShopContextStateFactory.get(this);
    }

    public Shop getShop() {
        return _shop;
    }

    public void setShop(Shop shop) {
        _shop = shop;
    }

    public ShopState getShopState() {
        return _shopState;
    }

    public void changeState(ShopState newState) {
        _shopState = newState;
    }

    public ShopStateLoop.Status execute() {
        return _shopState.execute();
    }
}