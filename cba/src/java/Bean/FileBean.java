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
public class FileBean extends PoBean {

    private int ATT_ID;
    private String ATT_TYPE;
    private String F_ACT_PATH;
    private String F_PATH;
    private String F_NAME;
    private String USER_ID;
  
    /**
     * @return the ATT_ID
     */
    public int getATT_ID() {
        return ATT_ID;
    }

    /**
     * @param ATT_ID the ATT_ID to set
     */
    public void setATT_ID(int ATT_ID) {
        this.ATT_ID = ATT_ID;
    }

    /**
     * @return the ATT_TYPE
     */
    public String getATT_TYPE() {
        return ATT_TYPE;
    }

    /**
     * @param ATT_TYPE the ATT_TYPE to set
     */
    public void setATT_TYPE(String ATT_TYPE) {
        this.ATT_TYPE = ATT_TYPE;
    }

    /**
     * @return the F_PATH
     */
    public String getF_PATH() {
        return F_PATH;
    }

    /**
     * @param F_PATH the F_PATH to set
     */
    public void setF_PATH(String F_PATH) {
        this.F_PATH = F_PATH;
    }

    /**
     * @return the F_NAME
     */
    public String getF_NAME() {
        return F_NAME;
    }

    /**
     * @param F_NAME the F_NAME to set
     */
    public void setF_NAME(String F_NAME) {
        this.F_NAME = F_NAME;
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
     * @return the F_ACT_PATH
     */
    public String getF_ACT_PATH() {
        return F_ACT_PATH;
    }

    /**
     * @param F_ACT_PATH the F_ACT_PATH to set
     */
    public void setF_ACT_PATH(String F_ACT_PATH) {
        this.F_ACT_PATH = F_ACT_PATH;
    }
    private static final Logger LOG = Logger.getLogger(FileBean.class.getName());
}
