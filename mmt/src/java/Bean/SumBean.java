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
// SUMMARY BEAN
public class SumBean {

    private String itm_code;
    private String itm_name;
    private String itm_loc;
    private float in_transit;
    private float op_stock;
    private float m_con;
    private float m_rec;
    private float net_av;
    private float qty;

    /**
     * @return the itm_name
     */
    public String getItm_name() {
        return itm_name;
    }

    /**
     * @param itm_name the itm_name to set
     */
    public void setItm_name(String itm_name) {
        this.itm_name = itm_name;
    }

    /**
     * @return the itm_loc
     */
    public String getItm_loc() {
        return itm_loc;
    }

    /**
     * @param itm_loc the itm_loc to set
     */
    public void setItm_loc(String itm_loc) {
        this.itm_loc = itm_loc;
    }

    /**
     * @return the in_transit
     */
    public float getIn_transit() {
        return in_transit;
    }

    /**
     * @param in_transit the in_transit to set
     */
    public void setIn_transit(float in_transit) {
        this.in_transit = in_transit;
    }

    /**
     * @return the op_stock
     */
    public float getOp_stock() {
        return op_stock;
    }

    /**
     * @param op_stock the op_stock to set
     */
    public void setOp_stock(float op_stock) {
        this.op_stock = op_stock;
    }

    /**
     * @return the m_con
     */
    public float getM_con() {
        return m_con;
    }

    /**
     * @param m_con the m_con to set
     */
    public void setM_con(float m_con) {
        this.m_con = m_con;
    }

    /**
     * @return the m_rec
     */
    public float getM_rec() {
        return m_rec;
    }

    /**
     * @param m_rec the m_rec to set
     */
    public void setM_rec(float m_rec) {
        this.m_rec = m_rec;
    }

    /**
     * @return the net_av
     */
    public float getNet_av() {
        return net_av;
    }

    /**
     * @param net_av the net_av to set
     */
    public void setNet_av(float net_av) {
        this.net_av = net_av;
    }

    /**
     * @return the itm_code
     */
    public String getItm_code() {
        return itm_code;
    }

    /**
     * @param itm_code the itm_code to set
     */
    public void setItm_code(String itm_code) {
        this.itm_code = itm_code;
    }

    /**
     * @return the qty
     */
    public float getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(float qty) {
        this.qty = qty;
    }


}
