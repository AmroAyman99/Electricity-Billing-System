package filehandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Old_Customer extends Customer {

    FileHandler f1 = new FileHandler();
    // private ArrayList<Bills> paidBills;
    private Meter houseMeter = new Meter();
    private String Complain;

    // Constructors
    public Old_Customer() {

    }

    public Old_Customer(String ssn, String name, String region, String address, String email, String meterCode, String UserName, String Password) {
        super(ssn, name, region, address, email);
        this.houseMeter.meterCode = meterCode;
        this.setPassword(Password);
        this.setUsername(UserName);

    }

    // Mutators
    public void setComplain(String complain) {
        this.Complain = complain;
    }

    public void setMeterCode(String meterCode) {
        this.houseMeter.meterCode = meterCode;
    }

    // Accessors
    public String getComplain() {
        return Complain;
    }

    public String getMeterCode() {
        return houseMeter.meterCode;
    }

    public String toString() {
        return getName() + "," + this.getSsn() + "," + this.getRegion() + "," + this.getAddress()
                + "," + this.getEmail() + "," + this.houseMeter.meterCode + "," + this.getUsername() + "," + this.getPassword() + "\n";
    }

    public boolean add_customer(Old_Customer Oc) {

        ArrayList<Old_Customer> customer_list = new ArrayList<Old_Customer>();

        customer_list = (ArrayList<Old_Customer>) f1.get_all_Customers();
        for (Old_Customer c : customer_list) {
            if (Oc.getSsn().equals(c.getSsn()) || Oc.getEmail().equals(c.getEmail()) || Oc.getMeterCode().equals(c.getMeterCode()) || Oc.getUsername().equals(c.getUsername()) || Oc.getPassword().equals(c.getPassword())) {
                return false;
            }
        }
        customer_list.add(Oc);
        f1.Update_Customers(customer_list);
        ArrayList<Meter>meterList=new ArrayList<Meter>();
        meterList=(ArrayList<Meter>)f1.get_all_meters();
        Meter m=new Meter();
        m.setMeterCode(Oc.getMeterCode());
        m.setLastPaid(LocalDate.now());
        meterList.add(m);
        f1.Update_Meter(meterList);
        return true;
    }

    public Double BillWithMc(String meter_code) {
        Double sum = 0.0;
        ArrayList<Bills> bill_List = new ArrayList<Bills>();
        bill_List = (ArrayList<Bills>) f1.get_all_bills();
        for (Bills c : bill_List) {
            if (c.getMeter_code().equals(meter_code) && !c.getPaid()) {
                sum += c.getAmount();
            }
        }
        return sum;
    }

    public void pay(String Meter_code) {
        ArrayList<Bills> bill_List = new ArrayList<Bills>();
        bill_List = (ArrayList<Bills>) f1.get_all_bills();
        
        for (Bills c : bill_List) {
            if (c.getMeter_code().equals(Meter_code) && !c.getPaid()) {
                c.setPaid(true);
            }
        }
        f1.Update_Bills(bill_List);
        ArrayList<Meter>Meter_List=new ArrayList<Meter>();
        Meter_List =(ArrayList<Meter>)f1.get_all_meters();
        for(Meter m:Meter_List){
            if(m.getMeterCode().equals(Meter_code)){
                m.setLastPaid(LocalDate.now());
                break;
            }
        }
        f1.Update_Meter(Meter_List);
    }

    public boolean MonthlyReading(double meter, String mc) {
        double j = 0;
        boolean flag=false;
        ArrayList<Meter> Meter_List = new ArrayList<Meter>();
        FileHandler f1 = new FileHandler();
        Meter_List = (ArrayList<Meter>) f1.get_all_meters();
        for (Meter m : Meter_List) {
            if (m.getMeterCode().equals(mc)) {
                double[] x = m.getMonthlyReading();
                j=x[1];
                x[0] = x[1];
                x[1] = meter;
                m.setMonthlyReading(x);
                flag=true;
                break;
            }
        }
        f1.Update_Meter(Meter_List);
        ArrayList<Old_Customer>Customer_list=new ArrayList<Old_Customer>();
        Customer_list=(ArrayList<Old_Customer>) f1.get_all_Customers();
        String ssn="";
        String region="";
        for(Old_Customer oc:Customer_list){
            if (oc.getMeterCode().equals(mc)) {
                ssn=oc.getSsn();
                region=oc.getRegion();
            }
        }
        ArrayList<Bills>bill=new ArrayList<Bills>();
        bill=(ArrayList<Bills>) f1.get_all_bills();
        Bills b=new Bills();
        b.setMeter_code(mc);
        b.setCo_ssn(ssn);
        b.setRegion(region);
        
        Operator op=new Operator();
        double amount=(meter-j)*op.tariff(mc);
        b.setAmount(amount);
        bill.add(b);
        f1.Update_Bills(bill);
        return flag;
    }

    public static void sendMail(String recepient, String mode) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "madeupeleccomp@gmail.com";
        String password = "3LeafClover";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, mode);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String MyAccountEmail, String recepient, String mode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            String subject = "none";
            String htmlCode = "example";
            if (mode.equals("bill_reminder")) {
                htmlCode = "<h3> Dear Customer,</h3><br/><b><t/>You have leftover bills from 3 months ago that have yet to be paid, please check and pay the remaining bills before paying the newest bill.</b>";
                subject = "Overdue bills";
            } else {
                htmlCode = "<h3> Dear Customer,</h3><br/><b><t/>Your new meter is ready, you can now proceed to other functions.</b>";
                subject = "New meter is ready";
            }

            message.setContent(htmlCode, "text/html");
            message.setSubject(subject);
            return message;

        } catch (Exception ex) {
            Logger.getLogger(Old_Customer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;

    }

}
