import java.math.BigDecimal;

public class PaymentCash extends Payment {
    public PaymentCash(BigDecimal amount) {
        super(amount);
    }

    @Override
    public String Describe() {
        return super.Describe() + " cash";
    }
}
