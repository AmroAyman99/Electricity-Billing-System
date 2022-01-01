package filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    
    public List<Bills> get_all_bills() {
        
        List<Bills> bill = new ArrayList<Bills>();
        try {
            
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\bill.txt");
            File f = new File("./bill.txt");

            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String[] details = line.split(",");                
                
                
                String Meter_Code = details[0];
                String Cus_Ssn = details[1];
                double Amount = Double.parseDouble(details[2]);
                String region = details[3];
                boolean paid = Boolean.parseBoolean(details[4]);
                Bills p = new Bills();
                
                
                p.setMeter_code(Meter_Code);
                p.setCo_ssn(Cus_Ssn);
                p.setAmount(Amount);
                p.setRegion(region);
                p.setPaid(paid);
                
                bill.add(p);                
            }
            sc.close();
            
        } catch (FileNotFoundException e) {            
            e.printStackTrace();
        }
        return bill;
        
    }
    
    public void Update_Bills(List<Bills> bill) {
        
        try {
            
            //FileWriter fw = new FileWriter("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\bill.txt", false);
            FileWriter fw = new FileWriter("./bill.txt", false);
            
            PrintWriter pw = new PrintWriter(fw, false);
            
            for (Bills p : bill) {
                
                pw.write(p.toString());
            }
            fw.close();            
            pw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<Old_Customer> get_all_Customers() {
        
        List<Old_Customer> customers = new ArrayList<Old_Customer>();
        try {
            
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\customers.txt");
            File f = new File("./customers.txt");
            
            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String[] details = line.split(",");                
                
                String name = (details[0]);                
                String Cus_Ssn = details[1];
                String region = details[2];
                String Address = details[3];
                String email = details[4];
                String Meter_Code = details[5];
                String UserName = details[6];
                String password = details[7];
                
                Old_Customer c = new Old_Customer();
                
                c.setName(name);
                c.setSsn(Cus_Ssn);
                c.setRegion(region);
                c.setAddress(Address);
                c.setEmail(email);
                c.setMeterCode(Meter_Code);
                c.setUsername(UserName);
                c.setPassword(password);
                
                customers.add(c);                
            }
            sc.close();
            
        } catch (FileNotFoundException e) {            
            e.printStackTrace();
            
        }
        return customers;
        
    }
    
    public void Update_Customers(List<Old_Customer> customers) {
        
        try {
            
            //FileWriter fw = new FileWriter("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\customers.txt", false);
            FileWriter fw = new FileWriter("./customers.txt", false);
            
            PrintWriter pw = new PrintWriter(fw, false);
            
            for (Old_Customer c : customers) {
                
                pw.write(c.toString());
            }
            fw.close();            
            pw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * **********
     */
    public ArrayList<Operator> get_all_operators() {
        
        ArrayList<Operator> operators = new ArrayList<Operator>();
        
        try {
            
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Operators.txt");
            File f = new File("./Operators.txt");

            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String[] details = line.split(",");
                
                String name = details[0];
                String username = details[1];
                String password = details[2];
                String ssn=details[3];
                Operator o = new Operator();
                
                o.setName(name);
                o.setUsername(username);
                o.setPassword(password); 
                o.setOp_Ssn(ssn);
                
                operators.add(o);
            }
            
            sc.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        }
        return operators;
        
    }
    
    public void Update_Operator(List<Operator> operators) {
        
        try {
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Operators.txt");
            File f = new File("./Operators.txt");
            
            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw, false);
            
            for(Operator o : operators) {
                
                pw.write(o.toString());
            }
            fw.close();            
            pw.close();            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Admin> get_all_admins() {
        
        ArrayList<Admin> admins = new ArrayList<Admin>();
        
        try {
            
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Admins.txt");
            File f = new File("./Admins.txt");

            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String[] details = line.split(",");
                
                String name = details[0];
                String username = details[1];
                String password = details[2];
                String ssn=details[3];
                Admin a = new Admin();
                
                a.setName(name);
                a.setUsername(username);
                a.setPassword(password);                
                a.setA_Ssn(ssn);
                admins.add(a);
            }
            
            sc.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        }
        return admins;
        
    }
    
    public void Update_Admin(List<Admin> admins) {
        
        try {
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Admins.txt");
            File f = new File("./Admins.txt");

            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw, false);
            
            for (Admin a : admins) {
                
                pw.write(a.toString());
            }
            fw.close();            
            pw.close();            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Meter> get_all_meters() {
        
        ArrayList<Meter> meters = new ArrayList<Meter>();
        
        try {
            
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Meters.txt");
            File f = new File("./Meters.txt");
            
            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String[] details = line.split(",");
                
                String meterCode = details[0];
                double[] monthlyReading = {Double.parseDouble(details[1]), Double.parseDouble(details[2])};
                boolean Stopped = Boolean.parseBoolean(details[3]);
                LocalDate lastPaid=LocalDate.parse(details[4]);
                Meter a = new Meter();
                
                a.setMeterCode(meterCode);
                a.setMonthlyReading(monthlyReading);
                a.setStopped(Stopped);
                a.setLastPaid(lastPaid);
                meters.add(a);
            }
            
            sc.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        }
        return meters;
        
    }
    
    public void Update_Meter(List<Meter> Meters) {
        
        try {
            //File f = new File("C:\\Users\\mostafa ayman\\Desktop\\2nd year sem 1\\ElectricityBillingSystem\\ElectricityBillingSystem\\Meters.txt");
            File f = new File("./Meters.txt");

            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw, false);
            
            for (Meter a : Meters) {
                
                pw.write(a.toString());
            }
            fw.close();            
            pw.close();            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
