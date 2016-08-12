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
public class WorkBean extends PoBean {

    private int id;
    private String CR_DATE;
    private String EXP_DATE;
    private String FR_DATE;
    private String BILL_DATE;
    private String OU;
    private String USER_ID;
    private String USER_NAME;
    private String SUP_NAME;
    private String SUP_ID;
    private String SITE;
    private String SITE_NAME;
    private String CUR;
    private String STATUS;
    private String TYPE;
    private String WO_DESC;
    private float RET_AMT;
    private float DEP_AMT;
    private float VAL;
    private float TOTAL_TAX;
    private float TOTAL_VAL;
    private String PO_TITLE;
    private String LAST_UP_DATE;
    private String P_USR_NAME;
    private String P_STG_NAME;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the CR_DATE
     */
    public String getCR_DATE() {
        return CR_DATE;
    }

    /**
     * @param CR_DATE the CR_DATE to set
     */
    public void setCR_DATE(String CR_DATE) {
        this.CR_DATE = CR_DATE;
    }

    /**
     * @return the EXP_DATE
     */
    public String getEXP_DATE() {
        return EXP_DATE;
    }

    /**
     * @param EXP_DATE the EXP_DATE to set
     */
    public void setEXP_DATE(String EXP_DATE) {
        this.EXP_DATE = EXP_DATE;
    }

    /**
     * @return the BILL_DATE
     */
    public String getBILL_DATE() {
        return BILL_DATE;
    }

    /**
     * @param BILL_DATE the BILL_DATE to set
     */
    public void setBILL_DATE(String BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    /**
     * @return the OU
     */
    public String getOU() {
        return OU;
    }

    /**
     * @param OU the OU to set
     */
    public void setOU(String OU) {
        this.OU = OU;
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
     * @return the SUP_NAME
     */
    public String getSUP_NAME() {
        return SUP_NAME;
    }

    /**
     * @param SUP_NAME the SUP_NAME to set
     */
    public void setSUP_NAME(String SUP_NAME) {
        this.SUP_NAME = SUP_NAME;
    }

    /**
     * @return the SUP_ID
     */
    public String getSUP_ID() {
        return SUP_ID;
    }

    /**
     * @param SUP_ID the SUP_ID to set
     */
    public void setSUP_ID(String SUP_ID) {
        this.SUP_ID = SUP_ID;
    }

    /**
     * @return the SITE
     */
    public String getSITE() {
        return SITE;
    }

    /**
     * @param SITE the SITE to set
     */
    public void setSITE(String SITE) {
        this.SITE = SITE;
    }

    /**
     * @return the SITE_NAME
     */
    public String getSITE_NAME() {
        return SITE_NAME;
    }

    /**
     * @param SITE_NAME the SITE_NAME to set
     */
    public void setSITE_NAME(String SITE_NAME) {
        this.SITE_NAME = SITE_NAME;
    }

    /**
     * @return the CUR
     */
    public String getCUR() {
        return CUR;
    }

    /**
     * @param CUR the CUR to set
     */
    public void setCUR(String CUR) {
        this.CUR = CUR;
    }

    /**
     * @return the STATUS
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     * @param STATUS the STATUS to set
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     * @return the TYPE
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * @param TYPE the TYPE to set
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    /**
     * @return the WO_DESC
     */
    public String getWO_DESC() {
        return WO_DESC;
    }

    /**
     * @param WO_DESC the WO_DESC to set
     */
    public void setWO_DESC(String WO_DESC) {
        this.WO_DESC = WO_DESC;
    }

    /**
     * @return the RET_AMT
     */
    public float getRET_AMT() {
        return RET_AMT;
    }

    /**
     * @param RET_AMT the RET_AMT to set
     */
    public void setRET_AMT(float RET_AMT) {
        this.RET_AMT = RET_AMT;
    }

    /**
     * @return the VAL
     */
    public float getVAL() {
        return VAL;
    }

    /**
     * @param VAL the VAL to set
     */
    public void setVAL(float VAL) {
        this.VAL = VAL;
    }

    /**
     * @return the TOTAL_TAX
     */
    public float getTOTAL_TAX() {
        return TOTAL_TAX;
    }

    /**
     * @param TOTAL_TAX the TOTAL_TAX to set
     */
    public void setTOTAL_TAX(float TOTAL_TAX) {
        this.TOTAL_TAX = TOTAL_TAX;
    }

    /**
     * @return the TOTAL_VAL
     */
    public float getTOTAL_VAL() {
        return TOTAL_VAL;
    }

    /**
     * @param TOTAL_VAL the TOTAL_VAL to set
     */
    public void setTOTAL_VAL(float TOTAL_VAL) {
        this.TOTAL_VAL = TOTAL_VAL;
    }

    /**
     * @return the PO_TITLE
     */
    public String getPO_TITLE() {
        return PO_TITLE;
    }

    /**
     * @param PO_TITLE the PO_TITLE to set
     */
    public void setPO_TITLE(String PO_TITLE) {
        this.PO_TITLE = PO_TITLE;
    }

    /**
     * @return the FR_DATE
     */
    public String getFR_DATE() {
        return FR_DATE;
    }

    /**
     * @param FR_DATE the FR_DATE to set
     */
    public void setFR_DATE(String FR_DATE) {
        this.FR_DATE = FR_DATE;
    }

    /**
     * @return the DEP_AMT
     */
    public float getDEP_AMT() {
        return DEP_AMT;
    }

    /**
     * @param DEP_AMT the DEP_AMT to set
     */
    public void setDEP_AMT(float DEP_AMT) {
        this.DEP_AMT = DEP_AMT;
    }

    /**
     * @return the LAST_UP_DATE
     */
    public String getLAST_UP_DATE() {
        return LAST_UP_DATE;
    }

    /**
     * @param LAST_UP_DATE the LAST_UP_DATE to set
     */
    public void setLAST_UP_DATE(String LAST_UP_DATE) {
        this.LAST_UP_DATE = LAST_UP_DATE;
    }

    /**
     * @return the P_USR_NAME
     */
    public String getP_USR_NAME() {
        return P_USR_NAME;
    }

    /**
     * @param P_USR_NAME the P_USR_NAME to set
     */
    public void setP_USR_NAME(String P_USR_NAME) {
        this.P_USR_NAME = P_USR_NAME;
    }

    /**
     * @return the P_STG_NAME
     */
    public String getP_STG_NAME() {
        return P_STG_NAME;
    }

    /**
     * @param P_STG_NAME the P_STG_NAME to set
     */
    public void setP_STG_NAME(String P_STG_NAME) {
        this.P_STG_NAME = P_STG_NAME;
    }
    private static final Logger LOG = Logger.getLogger(WorkBean.class.getName());

}
