import java.util.ArrayList;
import java.util.List;

//Example shop container
public class Shop {
    private long _orderId;
    private List<String> _items;
    private List<String> _quantity;
    private List<String> _price;
    private Payment _payment;
    private ShopState.Status _shopStateStatus;
    public boolean shopEnded = false;

    public Shop() {
        _shopStateStatus = ShopState.Status.Create;
        _items = new ArrayList<String>();
        _quantity = new ArrayList<String>();
        _price = new ArrayList<String>();
    }

    //Getters and setters
    public long getOrderId() {
        return _orderId;
    }

    public void setOrderId(long _orderId) {
        this._orderId = _orderId;
    }

    public List<String> getItems() {
        return _items;
    }

    public List<String> getQuantities() {
        return _quantity;
    }

    public List<String> getPrices() {
        return _price;
    }

    public Payment getPayment() {
        return _payment;
    }

    public void setPayment(Payment _payment) {
        this._payment = _payment;
    }

    public void setItems(List<String> _items) {
        this._items = _items;
    }

    public ShopState.Status getShopStateStatus() {
        return _shopStateStatus;
    }

    public void setShopStateStatus(ShopState.Status _shopStateStatus) {
        this._shopStateStatus = _shopStateStatus;
    }
    //End getters and setters


}
