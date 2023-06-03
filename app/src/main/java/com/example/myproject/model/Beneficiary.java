package com.example.myproject.model;


public class Beneficiary {
    private int id;
    private String email;
    private String sem;
    private String enroll;
    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String mar1;
    private String mar2;
    private String mar3;
    private String mar4;
    private String mar5;
    private String marprac;
    private String marattd;
    private String perc;
    private String grad;

    public String getSem() { return sem; }
    public void setSem(String sem) { this.sem = sem; }

    public String getEnroll() { return enroll; }
    public void setEnroll(String enroll) { this.enroll = enroll; }

    public String getSub1() { return sub1; }
    public void setSub1(String sub1) { this.sub1 = sub1; }

    public String getSub2() { return sub2; }
    public void setSub2(String sub2) { this.sub2 = sub2; }

    public String getSub3() { return sub3; }
    public void setSub3(String sub3) { this.sub3 = sub3; }

    public String getSub4() { return sub4; }
    public void setSub4(String sub4) { this.sub4 = sub4; }

    public String getSub5() { return sub5; }
    public void setSub5(String sub5) { this.sub5 = sub5; }

    public String getMar1() { return mar1; }
    public void setMar1(String mar1) { this.mar1 = mar1; }

    public String getMar2() { return mar2; }
    public void setMar2(String mar2) { this.mar2 = mar2; }

    public String getMar3() { return mar3; }
    public void setMar3(String mar3) { this.mar3 = mar3; }

    public String getMar4() { return mar4; }
    public void setMar4(String mar4) { this.mar4 = mar4; }

    public String getMar5() { return mar5; }
    public void setMar5(String mar5) { this.mar5 = mar5; }

    public String getMarprac() { return marprac; }
    public void setMarprac(String marprac) { this.marprac = marprac; }

    public String getMarattd() { return marattd; }
    public void setMarattd(String marattd) { this.marattd = marattd; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPerc() { return perc; }
    public void setPerc(String perc) { this.perc = perc; }

    public String getGrad() { return grad; }
    public void setGrad(String grad) { this.grad = grad; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public Beneficiary(String email, String enroll, String sem,
                       String sub1, String sub2, String sub3,
                       String sub4, String sub5, String mar1,
                       String mar2, String mar3, String mar4, String mar5,
                       String marprac, String marattd, String perc, String grad) {

        this.email = email;
        this.enroll = enroll;
        this.sem = sem;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
        this.sub5 = sub5;
        this.mar1 = mar1;
        this.mar2 = mar2;
        this.mar3 = mar3;
        this.mar4 = mar4;
        this.mar5 = mar5;
        this.marprac = marprac;
        this.marattd = marattd;
        this.perc = perc;
        this.grad = grad;
    }
}
