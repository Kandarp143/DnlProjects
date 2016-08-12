/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocInitTskDt;
import kp.beans.data.pojo.MocInitTskMst;
import kp.beans.mst.pojo.MocPlantMst;
import kp.beans.mst.pojo.MocUnitMst;
import kp.beans.user.pojo.MocUserMst;
import kp.dao.user.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class DropDown {

    public static void main(String[] args) {
        DropDown d = new DropDown();
//        d.getUnitDD();
        d.getPlantDD("NDS");
    }

    //unit drop down in moc create form
    public ArrayList<MocUnitMst> getUnitDD() {
        ArrayList<MocUnitMst> units = new ArrayList<>();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Query queryObj = session.getNamedQuery("getUnitDD");
            Query queryObj = session.createQuery("from MocUnitMst");
            units = (ArrayList<MocUnitMst>) queryObj.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(DropDown.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }
        Logger.getLogger(DropDown.class.getName()).log(Level.SEVERE, "units :" + units);
        return units;
    }

    //plant drop down in moc create form based on selected unit
    public ArrayList<MocPlantMst> getPlantDD(String unit) {
        ArrayList<MocPlantMst> plants = new ArrayList<>();
        MocPlantMst plant;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.createQuery("select plant.plantId,plant.plantName"
                    + "  from MocPlantMst as plant inner join plant.mocUnitMst as unit where unit.unitId = :unit"
                    + " ORDER BY plant.plantId ASC");
            queryObj.setString("unit", unit);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                plant = new MocPlantMst();
                plant.setPlantId((String) tuple[0]);
                plant.setPlantName((String) tuple[1]);

                plants.add(plant);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(DropDown.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }
        Logger.getLogger(DropDown.class.getName()).log(Level.SEVERE, "Plant Size : {0}", plants.size());
        return plants;
    }

    //doc to be updated user selector in moc create form
    public ArrayList<MocInitTskMst> getTskDD() {
        ArrayList<MocInitTskMst> ans = new ArrayList<>();
        MocInitTskMst bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getTskDD");
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocInitTskMst();
                bean.setDocId(((BigDecimal) tuple[0]).intValue());
                bean.setDocTitle((String) tuple[1]);
                ans.add(bean);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", e);
            session.close();
        } finally {
            session.close();
        }
        return ans;
    }
}
