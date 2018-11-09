import java.math.BigDecimal;

//Concrete check payment instance
public class PaymentCheck extends Payment {

    private int _checkNumber;

    public PaymentCheck(BigDecimal amount, int checkNumber){
        super(amount);
        _checkNumber = checkNumber;
    }

    public int getCheckNumber(){
        return _checkNumber;
    }

    @Override
    public String Describe(){
        return super.Describe() + ", check number " + _checkNumber;
    }
}