
import java.sql.Date;

/**
 *
 * @author Shakir
 */
public class TransObject {
    
    private int id;
    private double amount;
    private Date tarikh;
    private String detail;
    private String transaktype;
    
    public TransObject(int id, double amount, Date tarikh, String detail, String transaktype){
        this.id = id;
        this.amount = amount;
        this.tarikh = tarikh;
        this.detail = detail;
        this.transaktype = transaktype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTransaktype() {
        return transaktype;
    }

    public void setTransaktype(String transaktype) {
        this.transaktype = transaktype;
    }
}
