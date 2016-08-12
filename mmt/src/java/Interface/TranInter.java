/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.MitBean;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface TranInter {

    //for insert transaction
    public void insMIT_TRAN(ArrayList<MitBean> mitlist);

    //for get all transaction detail
    public ArrayList<MitBean> getMIT_TRAN();

    //for get spacific user transaction detail
    public ArrayList<MitBean> getMIT_TRAN(String user_id);

    //for get spacific tran as per mit
    public MitBean getMIT_TRAN2(String mit);

    //for get spacific tran as per itemcode to location
    public ArrayList<MitBean> getMIT_TRAN(String item_code, String location);

    //for get transaction detail of product
    public ArrayList<MitBean> getONHAND_TRAN(String item_code, String location);
}
