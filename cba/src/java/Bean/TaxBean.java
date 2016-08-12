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
public class TaxBean {

    private int TAX_ID;
    private String TAX_NAME;
    private String TAX_DESCR;
    private String TAX_TYPE;
    private String TAX_RATE;
    private String TAX_AMOUNT;

    /**
     * @return the TAX_ID
     */
    public int getTAX_ID() {
        return TAX_ID;
    }

    /**
     * @param TAX_ID the TAX_ID to set
     */
    public void setTAX_ID(int TAX_ID) {
        this.TAX_ID = TAX_ID;
    }

    /**
     * @return the TAX_NAME
     */
    public String getTAX_NAME() {
        return TAX_NAME;
    }

    /**
     * @param TAX_NAME the TAX_NAME to set
     */
    public void setTAX_NAME(String TAX_NAME) {
        this.TAX_NAME = TAX_NAME;
    }

    /**
     * @return the TAX_DESCR
     */
    public String getTAX_DESCR() {
        return TAX_DESCR;
    }

    /**
     * @param TAX_DESCR the TAX_DESCR to set
     */
    public void setTAX_DESCR(String TAX_DESCR) {
        this.TAX_DESCR = TAX_DESCR;
    }

    /**
     * @return the TAX_TYPE
     */
    public String getTAX_TYPE() {
        return TAX_TYPE;
    }

    /**
     * @param TAX_TYPE the TAX_TYPE to set
     */
    public void setTAX_TYPE(String TAX_TYPE) {
        this.TAX_TYPE = TAX_TYPE;
    }

    /**
     * @return the TAX_RATE
     */
    public String getTAX_RATE() {
        return TAX_RATE;
    }

    /**
     * @param TAX_RATE the TAX_RATE to set
     */
    public void setTAX_RATE(String TAX_RATE) {
        this.TAX_RATE = TAX_RATE;
    }

    /**
     * @return the TAX_AMOUNT
     */
    public String getTAX_AMOUNT() {
        return TAX_AMOUNT;
    }

    /**
     * @param TAX_AMOUNT the TAX_AMOUNT to set
     */
    public void setTAX_AMOUNT(String TAX_AMOUNT) {
        this.TAX_AMOUNT = TAX_AMOUNT;
    }
    private static final Logger LOG = Logger.getLogger(TaxBean.class.getName());

}
