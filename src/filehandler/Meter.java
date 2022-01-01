package filehandler;

import java.time.LocalDate;
import java.util.*;

public class Meter {

    public String meterCode;
    public double[] monthlyReading = {0, 0};
    public boolean stopped = false;
    public LocalDate lastPaid =LocalDate.now();

    public Meter() {
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public double[] getMonthlyReading() {
        return monthlyReading;
    }

    public LocalDate getLastPaid() {
        return lastPaid;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public void setMonthlyReading(double[] monthlyReading) {
        this.monthlyReading = monthlyReading;
    }

    public void setLastPaid(LocalDate lastPaid) {
        this.lastPaid = lastPaid;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getMeterCode() + "," + this.getMonthlyReading()[0] + "," + this.getMonthlyReading()[1] + "," + this.isStopped() +","+this.getLastPaid()+ "\n";
    }

}
