/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class LoginBean {

    /**
     * @return the LOG
     */
    public static Logger getLOG() {
        return LOG;
    }

    /**
     * @param aLOG the LOG to set
     */
    public static void setLOG(Logger aLOG) {
        LOG = aLOG;
    }

    private int SEQ;
    private String USER_ID;
    private String USER_NAME;
    private String PASS;
    private String NEW_PASS;
    private String LOGIN_TIME;
    private String LOGOUT_TIME;
    private String UNIT;
    private String EMAIL;
    private String USER_TYP;
    private String ACC_ROLE;

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

    /**
     * @return the NEW_PASS
     */
    public String getNEW_PASS() {
        return NEW_PASS;
    }

    /**
     * @param NEW_PASS the NEW_PASS to set
     */
    public void setNEW_PASS(String NEW_PASS) {
        this.NEW_PASS = NEW_PASS;
    }
    private static Logger LOG = Logger.getLogger(LoginBean.class.getName());

    /**
     * @return the UNIT
     */
    public String getUNIT() {
        return UNIT;
    }

    /**
     * @param UNIT the UNIT to set
     */
    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    /**
     * @return the EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * @param EMAIL the EMAIL to set
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    /**
     * @return the USER_TYP
     */
    public String getUSER_TYP() {
        return USER_TYP;
    }

    /**
     * @param USER_TYP the USER_TYP to set
     */
    public void setUSER_TYP(String USER_TYP) {
        this.USER_TYP = USER_TYP;
    }

    /**
     * @return the ACC_ROLE
     */
    public String getACC_ROLE() {
        return ACC_ROLE;
    }

    /**
     * @param ACC_ROLE the ACC_ROLE to set
     */
    public void setACC_ROLE(String ACC_ROLE) {
        this.ACC_ROLE = ACC_ROLE;
    }

   
}
