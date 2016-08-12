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
import kp.beans.data.pojo.MocInitMst;
import kp.beans.wf.pojo.MocWfTran;
import kp.dao.user.UserDao;
import kp.logic.DropDown;
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
public class TranDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        MocInitMst mst = new MocInitMst();
        mst.setCaseId(1);
        MocWfTran tran = new MocWfTran();
        tran.setMocInitMst(mst);
        tran.setAct(1);
        tran.setCmt("Test Transaction");
        tran.setStgC(1);
        tran.setStgN(2);
        tran.setUserC("02948");
        tran.setUserN("02949");

        TranDao tdao = new TranDao();
//        tdao.saveTransaction(tran);
//        tdao.completeTransaction(1, "02949");         \
        //  tdao.getMocList("Created", "01608");
//        tdao.isTranactionPending(2, 2);
//        tdao.getTransactions(1);

//        tdao.getMocStatusList("UH", "NDS", "02948");
    }

    //save transaction
    public int saveTransaction(MocWfTran tran) {
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "CALLED SAVE TRANSACTION");
        gm = new GeneralMethods();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Default Set
            tran.setActDate(gm.getSysDate(session));
            //Saving
            session.save(tran);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "TRANACTION INSERT CALLED");
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "STAGE CURRENT :" + tran.getStgC());
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "ACTION DONE :" + tran.getAct());
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "STAGE NEXT :", +tran.getStgN());
        return tran.getTranId();
    }

    //save list of transaction
    public void saveTransactions(ArrayList<MocWfTran> trans) {
        gm = new GeneralMethods();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (MocWfTran tran : trans) {
                //Default Set
                tran.setActDate(gm.getSysDate(session));
                //Saving
                session.save(tran);
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
    }

    //complete transaction's is_completed flag
    public void completeTransaction(int cid, String uid_n) {
        int result = 0;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.createQuery("update MocWfTran set is_completed ='Y' where tranId ="
                    + "(select max(tran.tranId)"
                    + "  from MocWfTran as tran inner join tran.mocInitMst as mst "
                    + "where mst.caseId = :cid and tran.userN = :uid_n)");
            queryObj.setInteger("cid", cid);
            queryObj.setString("uid_n", uid_n);
            result = queryObj.executeUpdate();
            System.out.println("Rows affected: " + result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(DropDown.class.getName()).log(Level.SEVERE, "Rows affected: " + result);
            session.close();
        } finally {
            session.close();
        }
    }

    //get all transaction of spacific moc case
    public ArrayList<MocWfTran> getTransactions(int cid) {
        ArrayList<MocWfTran> out = new ArrayList<>();
        MocWfTran bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getTransactions");
            queryObj.setInteger("cid", cid);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocWfTran();
                bean.setStgCname((String) tuple[0]);
                bean.setStgNname((String) tuple[1]);
                bean.setUserCname((String) tuple[2]);
                bean.setActname((String) tuple[3]);
                bean.setActDateString((String) tuple[4]);
                bean.setCmt((String) tuple[5]);
                bean.setUserNname((String) tuple[6]);
                bean.setIsCompletedStatus((String) tuple[7]);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "TRAN SELECTION : {0}", out.size());
        return out;
    }

    //get next page for spacific stage
    public String getNxtPage(int stg) {
        String out = "";
        switch (stg) {
            case 1://MOC Update
                out = "updateMoc";
                break;
            case 4://TS verification
                out = "saveTs";
                break;
            case 6://HSE Verification
                out = "saveHse";
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12://Cost Estimation
                out = "saveEng";
                break;
            case 14: // QC Verification
                out = "saveQc";
                break;
            case 20: //moc conformation
                out = "conformMoc";
                break;
            case 21://moc verification
                out = "saveApp";
                break;
            default://for approvals
                out = "saveApp";
                break;
        }
        return out;
    }

    //my workspace : only created moc
    public ArrayList<MocWfTran> getMocList(String status, String userId) {
        ArrayList<MocWfTran> out = new ArrayList<>();
        MocWfTran bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getMocListByStatus");
            queryObj.setString("status", status);
            queryObj.setString("userid", userId);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocWfTran();
                bean.setCaseId(((BigDecimal) tuple[0]).intValue());
                bean.setCrDateString((String) tuple[1]);
                bean.setCaseOwnerId((String) tuple[2]);
                bean.setCaseOwnerName((String) tuple[3]);
                bean.setUnitId((String) tuple[4]);
                bean.setPlantId((String) tuple[5]);
                bean.setCaseName((String) tuple[6]);
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "List Size : {0}", status);
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "List Size : {0}", userId);
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "List Size : {0}", out.size());
        return out;

    }

    //my workspace : pending moc
    public ArrayList<MocWfTran> getMocList(String userId) {
        ArrayList<MocWfTran> out = new ArrayList<>();
        MocWfTran bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getMocListByUser");
            queryObj.setString("userid", userId);
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocWfTran();
                bean.setCaseId(((BigDecimal) tuple[0]).intValue());
                bean.setCrDateString((String) tuple[1]);
                bean.setCaseOwnerId((String) tuple[2]);
                bean.setCaseOwnerName((String) tuple[3]);
                bean.setCaseName((String) tuple[4]);
                bean.setUnitId((String) tuple[5]);
                bean.setPlantId((String) tuple[6]);
                bean.setStgC(((BigDecimal) tuple[7]).intValue());
                bean.setStgCname((String) tuple[8]);
                bean.setAct(((BigDecimal) tuple[9]).intValue());
                bean.setActname((String) tuple[10]);
                bean.setUserC((String) tuple[11]);
                bean.setUserCname((String) tuple[12]);
                bean.setActDateString((String) tuple[13]);
                bean.setStgN(((BigDecimal) tuple[14]).intValue());
                bean.setStgNname((String) tuple[15]);
                bean.setNextPage(getNxtPage(((BigDecimal) tuple[14]).intValue()));
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
        return out;
    }

    //moc list with status based on user's acc role and unit
    public ArrayList<MocWfTran> getMocStatusList(String accRole, String unit, String userId) {
        ArrayList<MocWfTran> out = new ArrayList<>();
        MocWfTran bean = null;
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = null;
            if (accRole.equalsIgnoreCase("SYS") || accRole.equalsIgnoreCase("VP")) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "MOC STATUS USER ROLE :" + accRole);
                queryObj = session.getNamedQuery("getMocStatusListAll");

            } else if (accRole.equalsIgnoreCase("UH")) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "MOC STATUS USER ROLE :" + accRole);
                queryObj = session.getNamedQuery("getMocStatusListByUnit");
                queryObj.setString("unit", unit);
            } else {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "MOC STATUS USER ROLE :" + accRole);
                queryObj = session.getNamedQuery("getMocStatusListByUser");
                queryObj.setString("userid", userId);
            }
            Iterator iterator = queryObj.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                bean = new MocWfTran();
                bean.setCaseId(((BigDecimal) tuple[0]).intValue());
                bean.setCrDateString((String) tuple[1]);
                bean.setCaseOwnerId((String) tuple[2]);
                bean.setCaseOwnerName((String) tuple[3]);
                bean.setCaseName((String) tuple[4]);
                bean.setUnitId((String) tuple[5]);
                bean.setPlantId((String) tuple[6]);
                bean.setUserNname((String) tuple[7]);
                bean.setStgN(((BigDecimal) tuple[8]).intValue());
                bean.setStgNname((String) tuple[9]);
                bean.setMocStatus((String) tuple[10]);
                bean.setMocNo((String) tuple[11]);
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
        return out;
    }

    //parellel transaction check for moc 
    public Boolean isTranactionPending(int cid, int stg_c) {
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "IS TRAN PENDING CID AND STG :" + cid + "  : " + stg_c);
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean ans = false;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("isTranactionPending");
            queryObj.setInteger("cid", cid);
            queryObj.setInteger("sid", stg_c);
            if (queryObj.list().size() > 0) {
                ans = true;
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "IS TRAN PENDING SIZE :" + queryObj.list().size());
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
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "IS TRAN PENDING :" + ans);
        return ans;
    }

}
