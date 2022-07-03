package com.cycas.transaction.pojo;

public class Student {
    /**
     * s_id
     */
    private String sId;

    /**
     * s_name
     */
    private String sName;

    /**
     * s_birth
     */
    private String sBirth;

    /**
     * s_sex
     */
    private String sSex;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsBirth() {
        return sBirth;
    }

    public void setsBirth(String sBirth) {
        this.sBirth = sBirth;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }
}
