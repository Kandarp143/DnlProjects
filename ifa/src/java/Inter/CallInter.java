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
public interface CallInter {

    //convert String to words

    /**
     *
     * @param pattern
     * @return
     */
        public ArrayList<String> toWords(String pattern);

    //remove special character from string

    /**
     *
     * @param itemlist
     * @return
     */
    
    public ArrayList<Item> removeSC(ArrayList<Item> itemlist);

    // to check string contain word or not

    /**
     *
     * @param word
     * @param str
     * @return
     */
    
    public boolean checkWord(String word, String str);

}
