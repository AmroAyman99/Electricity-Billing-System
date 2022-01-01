package filehandler;

public class Customer extends User{

    private String ssn;
    private String name;
    private String region;
    private String address;
    private String email;

    // Constructor
    public Customer() {
		
	}
    
    
    public Customer(String ssn, String name, String region, String address, String email) {
        this.ssn = ssn;
        this.name = name;
        this.region = region;
        this.address = address;
        this.email = email;
    }
    
    // Mutators
    public void setSsn(String ssn){
        this.ssn=ssn;
    }    
    public void setName(String name) {
        this.name = name;
    }    
    public void setRegion(String region) {
        this.region = region;
    }    
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    // Accessors
    public String getSsn(){
        return ssn;
    }        
    public String getName() {
        return name;
    }
    public String getRegion(){
        return region;
    }        
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
}
