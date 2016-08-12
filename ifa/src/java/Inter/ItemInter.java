/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inter;

import Bean.Item;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface ItemInter {

    // to get item for mster or detail tables

    /**
     *
     * @return
     */
        public ArrayList<Item> getItem();

    /**
     *
     * @param grp
     * @return
     */
    public ArrayList<Item> getItem(String grp);

    /**
     *
     * @param grp
     * @param deldone
     * @return
     */
    public ArrayList<Item> getItem(String grp, ArrayList<Item> deldone);

    /**
     *
     * @param deldone
     * @return
     */
    public ArrayList<Item> getItem(ArrayList<Item> deldone);

    /**
     *
     * @return
     */
    public ArrayList<Item> getDelDoneItem();

    /**
     *
     * @return
     */
    public ArrayList<Item> getOnlyDelItem();

    /**
     *
     * @return
     */
    public ArrayList<Item> getDoneItem();

    /**
     *
     * @param reverted
     */
    public void delDoneItem(ArrayList<Item> reverted);

    /**
     *
     * @param selected
     * @return
     */
    public ArrayList<Item> getRelatedItem(Item selected);

    /**
     *
     * @param desc
     * @return
     */
    public ArrayList<Item> getRelatedItem(String desc);

    //drop down item group 

    /**
     *
     * @return
     */
        public ArrayList<Item> getItemGrp();

    //get item perameter any based on item no

    /**
     *
     * @param item_no
     * @param Perameter
     * @return
     */
        public String getItemPera(String item_no, String Perameter);

    //ins deleted Items to diffrent table

    /**
     *
     * @param deleted
     */
        public void insDelItem(ArrayList<Item> deleted);

    //ins deleted Items to diffrent table

    /**
     *
     * @param searched
     */
        public void insDoneItem(Item searched);

}
