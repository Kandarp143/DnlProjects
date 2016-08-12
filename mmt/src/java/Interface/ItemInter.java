/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.ItemBean;
import Bean.MitBean;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface ItemInter {

    //to get item master data
    public ArrayList<ItemBean> getALLitem();

    //to get item mster based on peramater
    public ArrayList<ItemBean> getALLitem(String peramater, String value);

    //to get item mster based on peramater
    public ArrayList<ItemBean> getALLitem(String peramater, String value, String uid);

//    //to insert no item with qty
//    public void insItem(ArrayList<ItemBean> itm);
//
//    public void insItem(ItemBean itm);

    //to update item qty
    public void updateQty(ArrayList<MitBean> itm);

}
