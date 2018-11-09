import java.util.Random;

//Concrete ShopState class to create a new shop.
public class ShopStateCreate extends ShopState {
    public ShopStateCreate(ShopContext context) {
        super(context, Status.Create);

        Shop shop = new Shop();

        shop.setOrderId(new Random().nextInt(1000));
        shop.setShopStateStatus(Status.Create);

        getShopContext().setShop(shop);
    }

    @Override
    public ShopStateLoop.Status execute() {
        System.out.println();
        System.out.println("*** NEW ORDER CREATED ***");
        getShopContext().changeState(new ShopStateAddItems(getShopContext()));

        return ShopStateLoop.Status.Continue;
    }
}
