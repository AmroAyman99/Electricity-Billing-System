package filehandler;

public class Bills {


    private String Meter_code;
    private String Customer_ssn;
    private Double Amount;
    private String Region;
    private boolean paid = false;
    

    public Bills() {

    }

    public Bills( String Meter_code, String Customer_ssn, Double Amount, String Region,boolean paid) {

        this.Meter_code = Meter_code;
        this.Customer_ssn = Customer_ssn;
        this.Amount = Amount;
        this.Region = Region;
        this.paid=paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public boolean getPaid(){
        return this.paid;
    }

    public void setCo_ssn(String Customer_ssn) {
        this.Customer_ssn = Customer_ssn;
    }

   

    public void setMeter_code(String Meter_code) {
        this.Meter_code = Meter_code;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public Double getAmount() {
        return Amount;
    }

    

    public String getCustomer_ssn() {
        return Customer_ssn;
    }

    public String getMeter_code() {
        return Meter_code;
    }

    public String getRegion() {
        return Region;
    }

    @Override
    public String toString() {

        return this.getMeter_code() + "," + this.getCustomer_ssn() + "," + this.getAmount() + "," + this.getRegion() +","+this.getPaid()+ "\n";
    }
    public String Print() {

        return "Meter Code :"+this.getMeter_code() + "\n The ssn :"+ this.getCustomer_ssn() + "\n The Amount :"+ this.getAmount() + "\n The Region :" + this.getRegion() +"\n Paid :"+this.getPaid()+ "\n";
    }
    /*Long Bill_Id =Long.parseLong( details[0]);
        Long Meter_Code =Long.parseLong(details[1]);
        Long Cus_Ssn =Long.parseLong( details[2]);
        double Amount = Double.parseDouble(details[3]);
        String region = details[4];*/
}
