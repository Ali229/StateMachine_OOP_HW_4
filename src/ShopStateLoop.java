public class ShopStateLoop {
    public Shop execute() throws Exception
    {
        return execute(null);
    }
    public Shop execute(Shop shop) throws Exception
    {
        ShopContext shopContext = shop == null
                ? new ShopContext()
                : new ShopContext(shop);

        while (shopContext.execute() == Status.Continue)
        {
        }
        System.out.println("*** ShopStateLoop.Status.Stopped ***");

        return shopContext.getShop();
    }

    public enum Status
    {
        Continue,
        Stop
    }
}
