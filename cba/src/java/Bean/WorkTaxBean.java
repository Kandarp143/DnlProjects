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
public class WorkTaxBean extends PoBean {

    private String TAX_TYPE;
    private float TAX_VAL;
    private String TAX_NAME;


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
     * @return the TAX_VAL
     */
    public float getTAX_VAL() {
        return TAX_VAL;
    }

    /**
     * @param TAX_VAL the TAX_VAL to set
     */
    public void setTAX_VAL(float TAX_VAL) {
        this.TAX_VAL = TAX_VAL;
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
    private static final Logger LOG = Logger.getLogger(WorkTaxBean.class.getName());
    
}
