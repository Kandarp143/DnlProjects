/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author 02948
 */
public class LoginBean {

    private int SEQ;
    private String USER_ID;
    private String USER_NAME;
    private String PASS;
    private String LOGIN_TIME;
    private String LOGOUT_TIME;

    /**
     * @return the SEQ
     */
    public int getSEQ() {
        return SEQ;
    }

    /**
     * @param SEQ the SEQ to set
     */
    public void setSEQ(int SEQ) {
        this.SEQ = SEQ;
    }

    /**
     * @return the USER_ID
     */
    public String getUSER_ID() {
        return USER_ID;
    }

    /**
     * @param USER_ID the USER_ID to set
     */
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

  
    /**
     * @return the PASS
     */
    public String getPASS() {
        return PASS;
    }

    /**
     * @param PASS the PASS to set
     */
    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    /**
     * @return the LOGIN_TIME
     */
    public String getLOGIN_TIME() {
        return LOGIN_TIME;
    }

    /**
     * @param LOGIN_TIME the LOGIN_TIME to set
     */
    public void setLOGIN_TIME(String LOGIN_TIME) {
        this.LOGIN_TIME = LOGIN_TIME;
    }

    /**
     * @return the LOGOUT_TIME
     */
    public String getLOGOUT_TIME() {
        return LOGOUT_TIME;
    }

    /**
     * @param LOGOUT_TIME the LOGOUT_TIME to set
     */
    public void setLOGOUT_TIME(String LOGOUT_TIME) {
        this.LOGOUT_TIME = LOGOUT_TIME;
    }

    /**
     * @return the USER_NAME
     */
    public String getUSER_NAME() {
        return USER_NAME;
    }

    /**
     * @param USER_NAME the USER_NAME to set
     */
    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

}
