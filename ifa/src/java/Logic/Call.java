/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.Item;
import Inter.CallInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class Call implements CallInter {

    static final Logger logger = Logger.getLogger(Call.class.getName());

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Call c = new Call();
        ArrayList<Item> a = new ArrayList<Item>();
        Item i1 = new Item();
        Item i2 = new Item();
        Item i3 = new Item();

        i1.setItem_no("1");
        i2.setItem_no("2");
        i3.setItem_no("3");

        i1.setItem_id("1");
        i2.setItem_id("2");
        i3.setItem_id("3");

        i1.setItem_desc("dfsd$#$#sdf#$%345Vvvsdf we rwe rw w r 3@$@#4 23 3423 42121~!~~~~");
        i2.setItem_desc("I@ am!@#@! k@!Nd!!darp");
        i3.setItem_desc("D&&PAK N$T@ITE L:RD");
        a.add(i1);
        a.add(i2);
        a.add(i3);

        //  c.toWords("dsf asdrfwet2332 dfSD#2 sdf#21 sdfsd ");
        //c.removeSC(a);
        c.checkWord("KANDARP", "MY NA@@M%%%E^^^ I#3s ###KaNdar##@p@@@#");

   

    }

    /**
     *
     * @param pattern
     * @return
     */
    @Override
    public ArrayList<String> toWords(String pattern) {
        String[] wordarray = pattern.split("\\s+");
        for (int i = 0; i < wordarray.length; i++) {
            wordarray[i] = wordarray[i].replaceAll("[^\\w]", "");
        }
        ArrayList<String> words = new ArrayList<String>(Arrays.<String>asList(wordarray));
        logger.log(Level.SEVERE, "String :{0} To words,", pattern);
//        for (String s : words) {
//            logger.log(Level.SEVERE, s);
//        }
        return words;
    }

    /**
     *
     * @param itemlist
     * @return
     */
    @Override
    public ArrayList<Item> removeSC(ArrayList<Item> itemlist) {
        ArrayList<Item> filtered = new ArrayList<Item>();
        for (Item que : itemlist) {
            Item ans = new Item();
            ans.setItem_id(que.getItem_id());
            ans.setItem_no(que.getItem_no());
            ans.setItem_desc(que.getItem_desc().toUpperCase().replaceAll("[^\\w\\s-]", ""));
            filtered.add(ans);
        }
        logger.log(Level.SEVERE, "Special Character Removed " + filtered.size());
//        for (Item i : filtered) {
//            logger.log(Level.SEVERE, i.getItem_desc());
//        }
        return filtered;
    }

    /**
     *
     * @param word
     * @param str
     * @return
     */
    @Override
    public boolean checkWord(String word, String str) {
//        logger.log(Level.SEVERE, "" + str.toUpperCase().replaceAll("[^\\w\\s-]", "").contains(word.toUpperCase()));
        return str.toUpperCase().replaceAll("[^\\w\\s-]", "").contains(word.toUpperCase());
    }

    /**
     *
     * @param column
     * @param table
     * @return
     */
    public int Get_Seq(String column, String table) {
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ans2 = 0;
        try {
            /*This Method for auto increment logic for all table*/
            String sql = "SELECT NVL(MAX(to_number(" + column + ")),0) FROM " + table;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans2 = rs.getInt(1);
            }
            ans2 = ++ans2;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return ans2;
    }

}
