/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.wf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.mst.pojo.MocUserSelection;
import kp.beans.wf.pojo.MocWfUser;
import kp.dao.user.UserDao;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class WfUserDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
//        MocWfUser usr = new MocWfUser();
//        MocWfUser usr2 = new MocWfUser();
//        MocInitMst mst = new MocInitMst();
//        mst.setCaseId(1);
//        usr.setMocInitMst(mst);
//        usr.setStgId(1);
//        usr.setUserId("02948");
//        usr2.setMocInitMst(mst);
//        usr2.setStgId(2);
//        usr2.setUserId("32543");
        WfUserDao dao = new WfUserDao();
        dao.getParellelUser(5, 2);
////
//        ArrayList<MocWfUser> usrs = new ArrayList<>();
//        usrs.add(usr);
//        usrs.add(usr2);
//
//        int setWorkFlowUsers = dao.setWorkFlowUsers(usrs);
//
////        dao.getWorkFlowSelection("NDS");
        dao.getNxtUser(1, 9);
    }

    
    //set workflow users for spacific case
    public int setWorkFlowUsers(ArrayList<MocWfUser> usrs) {
        gm = new GeneralMethods();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Saving
            usrs.stream().forEach((b) -> {

                session.save(b);
            });
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
        return usrs.get(1).getId();
    }

    //get list of user for selection
    public ArrayList<MocUserSelection> getWorkFlowSelection(String unit) {
        ArrayList<MocUserSelection> out = new ArrayList<>();
        MocUserSelection bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getWorkFlowSelection");
            queryObj.setString("unit", unit);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserSelection();
                bean.setStgId(((BigDecimal) tuple[0]).intValue());
                bean.setStgName((String) tuple[1]);
                bean.setRoleId((String) tuple[2]);
                bean.setRoleName((String) tuple[3]);
                bean.setUserId((String) tuple[4]);
                bean.setUserName((String) tuple[5]);
                bean.setUnitId((String) tuple[6]);
                out.add(bean);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "UNIT : {0}", unit);
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "USER SELECTION : {0}", out.size());
        return out;

    }

    //get selected workflow users
    public ArrayList<MocUserSelection> getWorkFlowUsers(int cid) {
        ArrayList<MocUserSelection> out = new ArrayList<>();
        MocUserSelection bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getWorkFlowUsers");
            queryObj.setInteger("cid", cid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserSelection();
                bean.setStgId(((BigDecimal) tuple[0]).intValue());
                bean.setStgName((String) tuple[1]);
                bean.setUserId((String) tuple[2]);
                bean.setUserName((String) tuple[3]);
                out.add(bean);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "CID : {0}", cid);
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "USERS : {0}", out.size());
        return out;
    }

    //get next user based on stage and case
    public ArrayList<MocUserSelection> getNxtUser(int cid, int sid) {
        ArrayList<MocUserSelection> out = new ArrayList<>();
        GeneralMethods m = new GeneralMethods();
        MocUserSelection bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getNxtUser");
            queryObj.setInteger("cid", cid);
            queryObj.setInteger("sid", sid);
            if (m.existsRec(queryObj)) {
                Iterator iterator = queryObj.list().iterator();
                while (iterator.hasNext()) {
                    Object[] tuple = (Object[]) iterator.next();
                    bean = new MocUserSelection();
                    bean.setUserId((String) tuple[0]);
                    bean.setUserName((String) tuple[1]);
                    bean.setStgId(((BigDecimal) tuple[2]).intValue());
                    out.add(bean);
                }
            } else {
                if (sid == 23) {
                    bean = new MocUserSelection();
                    bean.setUserId("0");
                    bean.setStgId(sid);
                    out.add(bean);
                } else if (sid == 7) {
                    //IF CURRENT STAGE WILL HSE VERI - HOPEFULL NEXT WILL COST ESTIMATION
                    bean = new MocUserSelection();
                    bean.setUserId("-");
                    bean.setStgId(sid);
                    out.add(bean);
                }else{
                    
                }
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "CID :{0} StageId:{1}", new Object[]{cid, sid});
        out.stream().forEach((b) -> {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Next User", b.getUserId());
        });
        return out;
    }

    //get previous user based on stage and case
    public ArrayList<MocUserSelection> getBackUser(int cid, int sid) {
        ArrayList<MocUserSelection> out = new ArrayList<>();
        MocUserSelection bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getBackUser");
            queryObj.setInteger("cid", cid);
            queryObj.setInteger("sid", sid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserSelection();
                bean.setStgId(((BigDecimal) tuple[0]).intValue());
                bean.setStgName((String) tuple[1]);
                bean.setUserId((String) tuple[2]);
                bean.setUserName((String) tuple[3]);
                out.add(bean);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "CID :{0} StageId:{1}", new Object[]{cid, sid});
        out.stream().forEach((b) -> {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Next User", b.getUserId());
        });
        return out;
    }

    //get parellel user based on stage and case
    public ArrayList<MocUserSelection> getParellelUser(int cid, int sid_c) {
        ArrayList<MocUserSelection> out = new ArrayList<>();
        MocUserSelection bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getParellelUser");
            queryObj.setInteger("cid", cid);
            queryObj.setInteger("sid", sid_c);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocUserSelection();
                bean.setUserId((String) tuple[0]);
                out.add(bean);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "PARREL USER CID :" + cid + ":STG:" + sid_c);
        out.stream().forEach((b) -> {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Parellel User ", b.getUserId());
        });
        return out;
    }
}
