package filehandler;

import java.util.ArrayList;
import javax.mail.search.FlagTerm;

public class Operator extends User {

    static FileHandler f1 = new FileHandler();

    private String name;
    private String op_Ssn;
    public Operator(String name, String username, String password,String Ssn) {
        this.name = name;
        this.setUsername(username);
        this.setPassword(password);
        this.op_Ssn=Ssn;
    }

    public Operator(String name) {
        super();
        this.name = name;

    }

    public Operator() {
        // TODO Auto-generated constructor stub
        super();
    }

    // Mutators
    public void setName(String n) {
        this.name = n;
    }

    public void setOp_Ssn(String op_Ssn) {
        this.op_Ssn = op_Ssn;
    }
    

    // Accessors
    public String getName() {
        return name;
    }

    public String getOp_Ssn() {
        return op_Ssn;
    }
    
    
    // Methods
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.name + "," + this.getUsername() + "," + this.getPassword() + ","+this.getOp_Ssn()+"\n";
    }

    public String collect(String meter) {
        ArrayList<Bills> bill = new ArrayList<Bills>();
        bill = (ArrayList<Bills>) f1.get_all_bills();

        double payment = 0;
        boolean flag = false;
        String s=null;
        s="The bill meter code is :" + meter+"\n";
        int i=1;
        for (Bills b:bill) {
            
            if (b.getMeter_code().equals(meter)) {
                s+="Bill No. "+i+ ": the Amount was :" + b.getAmount()+"\n";
                payment +=b.getAmount();
                flag = true;
                i++;
            }
            

        }
        if (flag == false) {

            s="\nThe customer is not found";

        } else {
            s+="\nThe total payment is:" + payment;
        }
        return s;
    }

    public String print_bill(String meter) {

        ArrayList<Bills> bill = new ArrayList<Bills>();
        bill = (ArrayList<Bills>) f1.get_all_bills();
        String s=null;
        boolean flag = false;
        for (Bills b:bill) {
            if (meter.equals(b.getMeter_code())) {
                s="The SSN of customer" + b.getCustomer_ssn();
                s+="\nThe Meter code" + b.getMeter_code();
                s+="\nThe Amount" + b.getAmount();
                s+="\nThe Region" + b.getRegion();
                flag = true;

            }

        }
        if (flag == false) {
            s="The Customer is not found";

        }
        return s;
    }

    public String view_bill(String region) {
        ArrayList<Bills> bill = new ArrayList<Bills>();
        bill = (ArrayList<Bills>) f1.get_all_bills();
        String s="";
        boolean flag=false;
        for (Bills b:bill) {
            if (region.equals(b.getRegion())) {
                s+="The Ssn of customer" + b.getCustomer_ssn();
                s+="\nThe Meter code" + b.getMeter_code();
                s+="\nThe Amount" + b.getAmount()+"\n\n";
                flag=true;
            }

        }
        if(!flag){
            s="No Bills found";
        }
        return s;

    }

    public String validation(String meter) {
        ArrayList<Meter> meters = new ArrayList<Meter>();
        meters = (ArrayList<Meter>) f1.get_all_meters();

        boolean flag = false;

        
        String s = null;
        for (Meter m:meters) {
            
            if (meter.equals(m.getMeterCode())) {
                flag = true;
                if (m.getMonthlyReading()[1] < m.getMonthlyReading()[0]) {
                    m.setStopped(true);
                    s="This Meter is having a Problem in validation\n";
                    break;
                }
                else{
                    s="This Meter Does not Have a problems in validation\n";
                    break;
                }
            }

        }

        if (flag == false) {
            s="the customer is not found\n";
        }
        return s;
    }

    public double tariff(String meter) {

        ArrayList<Meter> meters = new ArrayList<Meter>();
        meters = (ArrayList<Meter>) f1.get_all_meters();

        for (Meter m:meters) {
            if (meter.equals(m.getMeterCode())) {

                if (m.getMonthlyReading()[1] >= 0 && m.getMonthlyReading()[1] <= 50) {
                    return 0.48;
                } else if (m.getMonthlyReading()[1] -m.getMonthlyReading()[0]>= 51
                        && m.getMonthlyReading()[1] -m.getMonthlyReading()[0]<= 100) {
                    return 0.58;
                } else if (m.getMonthlyReading()[1] -m.getMonthlyReading()[0]>= 101
                        && m.getMonthlyReading()[1] -m.getMonthlyReading()[0]<= 200) {
                    return 0.77;
                } else if (m.getMonthlyReading()[1] -m.getMonthlyReading()[0]>= 201
                        && m.getMonthlyReading()[1] -m.getMonthlyReading()[0]<= 350) {
                    return 1.06;
                } else {
                    return 1.28;
                }
            }
        }

        return -1;

    }

    public boolean stop_meter(String meter) {

        ArrayList<Meter> meters = new ArrayList<Meter>();
        meters = (ArrayList<Meter>) f1.get_all_meters();
        boolean flag =false;
        for (Meter m:meters) {
            if (meter.equals(m.getMeterCode())) {
                m.setStopped(true);
                flag=true;
            }
        }
        return flag;
    }
    
    
    
    
    
}
