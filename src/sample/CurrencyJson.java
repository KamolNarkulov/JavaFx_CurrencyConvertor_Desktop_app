package sample;

public class CurrencyJson {
    private String Ccy;
    private String Rate;
    private String Diff;
    private String Date;

    CurrencyJson(String Ccy, String Rate, String Diff, String Date){
        this.Ccy = Ccy;
        this.Rate = Rate;
        this.Diff = Diff;
        this.Date = Date;
    }

    public String getCcy() {
        return Ccy;
    }

    public String getRate() {
        return Rate;
    }

    public String getDiff() {
        return Diff;
    }

    public String getDate() {
        return Date;
    }


}



