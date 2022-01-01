package filehandler;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    static FileHandler f1 = new FileHandler();
    private String name;
    private String A_Ssn;

    // Constructors
    public Admin(String username, String password, String name, String Ssn) {
        super(name, password);
        this.name = name;
        this.A_Ssn = Ssn;
    }

    public Admin() {
        super();
    }

    // Mutators
    public void setName(String n) {
        this.name = n;
    }

    public void setA_Ssn(String A_Ssn) {
        this.A_Ssn = A_Ssn;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public String getA_Ssn() {
        return A_Ssn;
    }

    @Override
    public String toString() {
        return this.name + "," + this.getUsername() + "," + this.getPassword() + "," + this.getA_Ssn() + "\n";
    }

    public ArrayList<Bills> view_Bills_Of_Specific_Region(String region) {

        ArrayList<Bills> Bills_List1 = new ArrayList<Bills>();
        Bills_List1 = (ArrayList<Bills>) f1.get_all_bills();
        ArrayList<Bills> Bills_List2 = new ArrayList<Bills>();
        for (Bills x : Bills_List1) {

            if (x.getRegion().equalsIgnoreCase(region)) {
                Bills_List2.add(x);
            }

        }
        return Bills_List2;
    }

    public User Search(String ssn, String role) {
        Old_Customer oc = new Old_Customer();
        Admin a = new Admin();
        Operator op = new Operator();
        boolean flag = false;
        oc.setPassword(-1 + "");
        a.setPassword(-1 + "");
        op.setPassword(-1 + "");

        if (role.equalsIgnoreCase("customer")) {

            ArrayList<Old_Customer> Customers_list = new ArrayList<Old_Customer>();
            Customers_list = (ArrayList<Old_Customer>) f1.get_all_Customers();

            for (Old_Customer x : Customers_list) {

                if (x.getSsn().equals(ssn)) {
                    oc.setAddress(x.getAddress());
                    oc.setMeterCode(x.getMeterCode());
                    oc.setEmail(x.getEmail());
                    oc.setName(x.getName());
                    oc.setRegion(x.getRegion());
                    oc.setPassword(x.getPassword());
                    oc.setUsername(x.getUsername());
                    oc.setSsn(x.getSsn());
                    break;
                }

            }
            return oc;
        } else if (role.equalsIgnoreCase("Operator")) {

            ArrayList<Operator> Operator_list = new ArrayList<Operator>();
            Operator_list = (ArrayList<Operator>) f1.get_all_operators();

            for (Operator x : Operator_list) {

                if (x.getOp_Ssn().equals(ssn)) {
                    op.setPassword(x.getPassword());
                    op.setUsername(x.getUsername());
                    op.setName(x.getName());
                    op.setOp_Ssn(x.getOp_Ssn());

                    break;
                }

            }
            return op;
        } else {

            ArrayList<Admin> Admin_list = new ArrayList<Admin>();
            Admin_list = (ArrayList<Admin>) f1.get_all_admins();

            for (Admin x : Admin_list) {

                if (x.getA_Ssn().equals(ssn)) {
                    a.setPassword(x.getPassword());
                    a.setUsername(x.getUsername());
                    a.setName(x.getName());
                    a.setA_Ssn(x.getA_Ssn());
                    break;

                }
            }

            return a;
        }

    }

    public boolean Add(User c) {

         if (c instanceof Admin) {
            Admin c2 = new Admin();
            Admin c1 = new Admin();
            c2 = (Admin) c;
            c1 = c2;

            List<Admin> Admins_list = new ArrayList<Admin>();
            Admins_list = (ArrayList<Admin>) f1.get_all_admins();
            for(Admin a:Admins_list){
                if(a.getA_Ssn().equals(c1.getA_Ssn())||a.getUsername().equals(c1.getUsername())||a.getPassword().equals(getPassword())){
                    return false;
                }
            }
            Admins_list.add(c1);
            f1.Update_Admin(Admins_list);

        } else if (c instanceof Operator) {
            Operator c2 = new Operator();
            Operator c1 = new Operator();
            c2 = (Operator) c;
            c1 = c2;

            List<Operator> Operators_list = new ArrayList<Operator>();
            Operators_list = (ArrayList<Operator>) f1.get_all_operators();
            for(Operator a:Operators_list){
                if(a.getOp_Ssn().equals(c1.getOp_Ssn())||a.getUsername().equals(c1.getUsername())||a.getPassword().equals(getPassword())){
                    return false;
                }
            }
            Operators_list.add(c1);
            f1.Update_Operator(Operators_list);

        }
         return true;
    }

    public boolean Update(String ssn, String role, User z) {
        boolean Updated = false;
        if (role.equalsIgnoreCase("customer")) {

            Old_Customer c1 = new Old_Customer();
            c1 = (Old_Customer) z;

            ArrayList<Old_Customer> Customer_List = new ArrayList<Old_Customer>();
            Customer_List = (ArrayList<Old_Customer>) f1.get_all_Customers();
            String s = "";
            String OldSsn = "";
            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Old_Customer x : Customer_List) {
                    if (x.getSsn().equals(ssn)) {
                        s = x.getMeterCode();
                        OldSsn = x.getSsn();
                        x.setName(c1.getName());
                        x.setRegion(c1.getRegion());
                        x.setAddress(c1.getAddress());
                        x.setMeterCode(c1.getMeterCode());
                        x.setEmail(c1.getEmail());
                        x.setUsername(c1.getUsername());
                        x.setPassword(c1.getPassword());
                        x.setSsn(c1.getSsn());
                        Updated = true;
                    }

                }
                f1.Update_Customers(Customer_List);
            }

            ArrayList<Meter> Meter_list = new ArrayList<Meter>();
            Meter_list = (ArrayList<Meter>) f1.get_all_meters();
            for (Meter m : Meter_list) {
                if (m.getMeterCode().equals(s)) {
                    m.setMeterCode(c1.getMeterCode());
                }
            }
            f1.Update_Meter(Meter_list);

            ArrayList<Bills> bill_list = new ArrayList<Bills>();
            bill_list = (ArrayList<Bills>) f1.get_all_bills();
            for (Bills b : bill_list) {
                if (b.getMeter_code().equals(s)) {
                    b.setMeter_code(c1.getMeterCode());
                }
                if (b.getCustomer_ssn().equals(OldSsn)) {
                    b.setCo_ssn(c1.getSsn());
                }
            }
            f1.Update_Bills(bill_list);
        } else if (role.equalsIgnoreCase("admin")) {

            Admin c1 = new Admin();
            c1 = (Admin) z;

            ArrayList<Admin> Admins_list = new ArrayList<Admin>();
            Admins_list = (ArrayList<Admin>) f1.get_all_admins();

            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Admin x : Admins_list) {
                    if (x.getA_Ssn().equals(ssn)) {
                        x.setName(c1.getName());
                        x.setUsername(c1.getUsername());
                        x.setPassword(c1.getPassword());
                        x.setA_Ssn(c1.getA_Ssn());
                        Updated = true;
                    }
                }
            }

            f1.Update_Admin(Admins_list);

        } else {

            Operator c1 = new Operator();
            c1 = (Operator) z;

            ArrayList<Operator> Operators_list = new ArrayList<Operator>();
            Operators_list = (ArrayList<Operator>) f1.get_all_operators();

            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Operator x : Operators_list) {

                    if (x.getOp_Ssn().equals(ssn)) {
                        x.setName(c1.getName());
                        x.setUsername(c1.getUsername());
                        x.setPassword(c1.getPassword());
                        x.setOp_Ssn(c1.getOp_Ssn());
                        Updated = true;

                    }

                }
            }

            f1.Update_Operator(Operators_list);

        }
        return Updated;

    }

    public boolean Delete(String ssn, String role) {

        boolean deleted = false;
        if (role.equalsIgnoreCase("customer")) {
            Old_Customer oc = new Old_Customer();
            ArrayList<Old_Customer> Customer_List = new ArrayList<Old_Customer>();
            Customer_List = (ArrayList<Old_Customer>) f1.get_all_Customers();
            String oldMeter = "";
            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Old_Customer x : Customer_List) {
                    if (x.getSsn().equals(ssn)) {
                        oldMeter = x.getMeterCode();
                        oc = x;
                        deleted = true;
                    }

                }
                Customer_List.remove(oc);
                f1.Update_Customers(Customer_List);
            }

            ArrayList<Meter> Meter_list = new ArrayList<Meter>();
            Meter_list = (ArrayList<Meter>) f1.get_all_meters();
            Meter me = new Meter();
            for (Meter m : Meter_list) {
                if (m.getMeterCode().equals(oldMeter)) {
                    me = m;
                }
            }
            Meter_list.remove(me);
            f1.Update_Meter(Meter_list);
            Bills bi = new Bills();
            ArrayList<Bills> bill_list = new ArrayList<Bills>();
            bill_list = (ArrayList<Bills>) f1.get_all_bills();
            for (Bills b : bill_list) {
                if (b.getMeter_code().equals(oldMeter)) {
                    bi = b;
                }
            }
            bill_list.remove(bi);
            f1.Update_Bills(bill_list);
        } else if (role.equalsIgnoreCase("admin")) {

            ArrayList<Admin> Admins_list = new ArrayList<Admin>();
            Admins_list = (ArrayList<Admin>) f1.get_all_admins();
            Admin ad = new Admin();
            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Admin x : Admins_list) {
                    if (x.getA_Ssn().equals(ssn)) {
                        ad = x;
                        deleted = true;
                    }
                }
                Admins_list.remove(ad);
            }

            f1.Update_Admin(Admins_list);

        } else {

            ArrayList<Operator> Operators_list = new ArrayList<Operator>();
            Operators_list = (ArrayList<Operator>) f1.get_all_operators();
            Operator op = new Operator();
            if (!Search(ssn, role).getPassword().equals(-1 + "")) {
                for (Operator x : Operators_list) {

                    if (x.getOp_Ssn().equals(ssn)) {
                        op = x;
                        deleted = true;
                    }

                }
                Operators_list.remove(op);
            }

            f1.Update_Operator(Operators_list);

        }
        return deleted;

    }

    public String Consumption_statistics_of_specific_region(String region) {

        ArrayList<Old_Customer> Customers_list = new ArrayList<Old_Customer>();
        Customers_list = (ArrayList<Old_Customer>) f1.get_all_Customers();

        ArrayList<String> Meter_Codes = new ArrayList<String>();

        for (Old_Customer x : Customers_list) {

            if (x.getRegion().equalsIgnoreCase(region)) {
                Meter_Codes.add(x.getMeterCode());
            }

        }
        ArrayList<Meter> Meters_list = new ArrayList<Meter>();
        Meters_list = (ArrayList<Meter>) f1.get_all_meters();
        long v1 = 0, v2 = 0, v3 = 0, v4 = 0;
        double percent1;
        double percent2;
        double percent3;
        String s = null;
        for (String c : Meter_Codes) {

            for (Meter x : Meters_list) {

                if (c.equals(x.getMeterCode())) {
                    if (x.getMonthlyReading()[1] - x.getMonthlyReading()[0] < 50) {
                        v1++;
                        v4++;
                    } else if (x.getMonthlyReading()[1] - x.getMonthlyReading()[0] > 50 && x.getMonthlyReading()[1] - x.getMonthlyReading()[0] < 80) {
                        v2++;
                        v4++;
                    } else if (x.getMonthlyReading()[1] - x.getMonthlyReading()[0] > 80) {
                        v3++;
                        v4++;
                    }

                }

            }

        }
        percent1 = ((double) v1 / v4) * 100;//NAN
        percent2 = ((double) v2 / v4) * 100;//nan
        percent3 = ((double) v3 / v4) * 100;//nan

        s = "Percentage of Meters (Monthly Reading <50) :" + percent1
                + "\nPercentage of Meters ( 50 < Monthly Reading < 80) : " + percent2
                + "\nPercentage of Meters ( Monthly Reading > 80) : " + percent3
                + "\nThe Total Number of Meters in this Region : " + v4;

        return s;
    }

    public double Total_Collected() {
        double Total = 0;
        ArrayList<Bills> Bills_List1 = new ArrayList<Bills>();
        Bills_List1 = (ArrayList<Bills>) f1.get_all_bills();

        for (Bills x : Bills_List1) {
            if (x.getPaid()) {
                Total += x.getAmount();
            }

        }
        return Total;
    }

}
