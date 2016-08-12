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
public class InvBean {

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

    private int INVOICE_ID;

    private String INVOICE_NUM;
    private String INVOICE_TYPE_LOOKUP_CODE;
    private String INVOICE_DATE;
    private int VENDOR_ID;
    private String VENDOR_NUM;
    private String VENDOR_NAME;
    private int VENDOR_SITE_ID;
    private String VENDOR_SITE_CODE;
    private float INVOICE_AMOUNT;
    private String INVOICE_CURRENCY_CODE;
    private int TERMS_ID;
    private String DESCRIPTION;
    private String CREATION_DATE;
    private String GROUP_ID;
    private String SOURCE;
    private int INV_REQ_ID;
    private int ACCpayID;
    private int ERP_PO_NO;
    private String PO_NUM;
    private String PROJ;
    private String TASK;
    private String AMOUNT_PAID;

    /**
     * @return the INVOICE_ID
     */
    public int getINVOICE_ID() {
        return INVOICE_ID;
    }

    /**
     * @param INVOICE_ID the INVOICE_ID to set
     */
    public void setINVOICE_ID(int INVOICE_ID) {
        this.INVOICE_ID = INVOICE_ID;
    }

    /**
     * @return the INVOICE_NUM
     */
    public String getINVOICE_NUM() {
        return INVOICE_NUM;
    }

    /**
     * @param INVOICE_NUM the INVOICE_NUM to set
     */
    public void setINVOICE_NUM(String INVOICE_NUM) {
        this.INVOICE_NUM = INVOICE_NUM;
    }

    /**
     * @return the INVOICE_TYPE_LOOKUP_CODE
     */
    public String getINVOICE_TYPE_LOOKUP_CODE() {
        return INVOICE_TYPE_LOOKUP_CODE;
    }

    /**
     * @param INVOICE_TYPE_LOOKUP_CODE the INVOICE_TYPE_LOOKUP_CODE to set
     */
    public void setINVOICE_TYPE_LOOKUP_CODE(String INVOICE_TYPE_LOOKUP_CODE) {
        this.INVOICE_TYPE_LOOKUP_CODE = INVOICE_TYPE_LOOKUP_CODE;
    }

    /**
     * @return the INVOICE_DATE
     */
    public String getINVOICE_DATE() {
        return INVOICE_DATE;
    }

    /**
     * @param INVOICE_DATE the INVOICE_DATE to set
     */
    public void setINVOICE_DATE(String INVOICE_DATE) {
        this.INVOICE_DATE = INVOICE_DATE;
    }

    /**
     * @return the VENDOR_ID
     */
    public int getVENDOR_ID() {
        return VENDOR_ID;
    }

    /**
     * @param VENDOR_ID the VENDOR_ID to set
     */
    public void setVENDOR_ID(int VENDOR_ID) {
        this.VENDOR_ID = VENDOR_ID;
    }

    /**
     * @return the VENDOR_NUM
     */
    public String getVENDOR_NUM() {
        return VENDOR_NUM;
    }

    /**
     * @param VENDOR_NUM the VENDOR_NUM to set
     */
    public void setVENDOR_NUM(String VENDOR_NUM) {
        this.VENDOR_NUM = VENDOR_NUM;
    }

    /**
     * @return the VENDOR_NAME
     */
    public String getVENDOR_NAME() {
        return VENDOR_NAME;
    }

    /**
     * @param VENDOR_NAME the VENDOR_NAME to set
     */
    public void setVENDOR_NAME(String VENDOR_NAME) {
        this.VENDOR_NAME = VENDOR_NAME;
    }

    /**
     * @return the VENDOR_SITE_ID
     */
    public int getVENDOR_SITE_ID() {
        return VENDOR_SITE_ID;
    }

    /**
     * @param VENDOR_SITE_ID the VENDOR_SITE_ID to set
     */
    public void setVENDOR_SITE_ID(int VENDOR_SITE_ID) {
        this.VENDOR_SITE_ID = VENDOR_SITE_ID;
    }

    /**
     * @return the VENDOR_SITE_CODE
     */
    public String getVENDOR_SITE_CODE() {
        return VENDOR_SITE_CODE;
    }

    /**
     * @param VENDOR_SITE_CODE the VENDOR_SITE_CODE to set
     */
    public void setVENDOR_SITE_CODE(String VENDOR_SITE_CODE) {
        this.VENDOR_SITE_CODE = VENDOR_SITE_CODE;
    }

    /**
     * @return the INVOICE_AMOUNT
     */
    public float getINVOICE_AMOUNT() {
        return INVOICE_AMOUNT;
    }

    /**
     * @param INVOICE_AMOUNT the INVOICE_AMOUNT to set
     */
    public void setINVOICE_AMOUNT(float INVOICE_AMOUNT) {
        this.INVOICE_AMOUNT = INVOICE_AMOUNT;
    }

    /**
     * @return the INVOICE_CURRENCY_CODE
     */
    public String getINVOICE_CURRENCY_CODE() {
        return INVOICE_CURRENCY_CODE;
    }

    /**
     * @param INVOICE_CURRENCY_CODE the INVOICE_CURRENCY_CODE to set
     */
    public void setINVOICE_CURRENCY_CODE(String INVOICE_CURRENCY_CODE) {
        this.INVOICE_CURRENCY_CODE = INVOICE_CURRENCY_CODE;
    }

    /**
     * @return the TERMS_ID
     */
    public int getTERMS_ID() {
        return TERMS_ID;
    }

    /**
     * @param TERMS_ID the TERMS_ID to set
     */
    public void setTERMS_ID(int TERMS_ID) {
        this.TERMS_ID = TERMS_ID;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     * @return the CREATION_DATE
     */
    public String getCREATION_DATE() {
        return CREATION_DATE;
    }

    /**
     * @param CREATION_DATE the CREATION_DATE to set
     */
    public void setCREATION_DATE(String CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    /**
     * @return the GROUP_ID
     */
    public String getGROUP_ID() {
        return GROUP_ID;
    }

    /**
     * @param GROUP_ID the GROUP_ID to set
     */
    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    /**
     * @return the SOURCE
     */
    public String getSOURCE() {
        return SOURCE;
    }

    /**
     * @param SOURCE the SOURCE to set
     */
    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    /**
     * @return the INV_REQ_ID
     */
    public int getINV_REQ_ID() {
        return INV_REQ_ID;
    }

    /**
     * @param INV_REQ_ID the INV_REQ_ID to set
     */
    public void setINV_REQ_ID(int INV_REQ_ID) {
        this.INV_REQ_ID = INV_REQ_ID;
    }

    /**
     * @return the ACCpayID
     */
    public int getACCpayID() {
        return ACCpayID;
    }

    /**
     * @param ACCpayID the ACCpayID to set
     */
    public void setACCpayID(int ACCpayID) {
        this.ACCpayID = ACCpayID;
    }

    /**
     * @return the ERP_PO_NO
     */
    public int getERP_PO_NO() {
        return ERP_PO_NO;
    }

    /**
     * @param ERP_PO_NO the ERP_PO_NO to set
     */
    public void setERP_PO_NO(int ERP_PO_NO) {
        this.ERP_PO_NO = ERP_PO_NO;
    }

    /**
     * @return the PO_NUM
     */
    public String getPO_NUM() {
        return PO_NUM;
    }

    /**
     * @param PO_NUM the PO_NUM to set
     */
    public void setPO_NUM(String PO_NUM) {
        this.PO_NUM = PO_NUM;
    }

    /**
     * @return the AMOUNT_PAID
     */
    public String getAMOUNT_PAID() {
        return AMOUNT_PAID;
    }

    /**
     * @param AMOUNT_PAID the AMOUNT_PAID to set
     */
    public void setAMOUNT_PAID(String AMOUNT_PAID) {
        this.AMOUNT_PAID = AMOUNT_PAID;
    }
    private static Logger LOG = Logger.getLogger(InvBean.class.getName());

    /**
     * @return the PROJ
     */
    public String getPROJ() {
        return PROJ;
    }

    /**
     * @param PROJ the PROJ to set
     */
    public void setPROJ(String PROJ) {
        this.PROJ = PROJ;
    }

    /**
     * @return the TASK
     */
    public String getTASK() {
        return TASK;
    }

    /**
     * @param TASK the TASK to set
     */
    public void setTASK(String TASK) {
        this.TASK = TASK;
    }

}
