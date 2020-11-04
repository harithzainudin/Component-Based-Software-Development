/**
 *
 * @author Shakir
 */
public class BankObject {
    
    private int id;
    private String bankname;
    
    public BankObject(){
        
    }
    
    public BankObject( int id,String bankname){
        this.id = id;
        this.bankname = bankname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    
    
}
