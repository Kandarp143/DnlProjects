/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import kp.dao.user.UserDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocInitMst;
import kp.beans.data.pojo.MocTsMst;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class TsDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        MocTsMst ts = new MocTsMst();
        MocInitMst mst = new MocInitMst();
        mst.setCaseId(1);
        ts.setI1("on");
        ts.setI2("ok");
        ts.setI3("on");
        ts.setI4("on");
        ts.setUserId("0234948");
        ts.setActDate(new GeneralMethods().getSysDate());
        ts.setMocInitMst(mst);
        ts.setCaseId(1);

        TsDao td = new TsDao();
//        td.saveTs(ts);
        td.updateTs(ts);

//        td.deleteTs(1);
    }

    public int saveTs(MocTsMst bean) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        gm = new GeneralMethods();
        try {
            final Transaction transaction = session.beginTransaction();
            try {
                bean.setActDate(gm.getSysDate(session));
                session.save(bean);
                transaction.commit();
            } catch (Exception ex) {
                // Log the exception here
                transaction.rollback();
                Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "Exception : ");
                throw ex;
            }
        } finally {
            session.close();
        }
        Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "CID : " + bean.getCaseId());
        return bean.getCaseId();

    }

    public int updateTs(MocTsMst bean) {
        Transaction tx = null;
        Session session = null;
        gm = new GeneralMethods();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocTsMst fetchmst = (MocTsMst) session.get(MocTsMst.class, bean.getMocInitMst().getCaseId());
            fetchmst.setI1(bean.getI1());
            fetchmst.setI2(bean.getI2());
            fetchmst.setI3(bean.getI3());
            fetchmst.setI4(bean.getI4());
            fetchmst.setActDate(gm.getSysDate());
            fetchmst.setUserId(bean.getUserId());
            session.clear();
            session.update(fetchmst);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            session.close();
        } finally {
            session.close();
        }
        return bean.getCaseId();
    }

    public void deleteTs(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocTsMst fetchmst = (MocTsMst) session.get(MocTsMst.class, cid);
            session.delete(fetchmst);
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

    public MocTsMst fecthTs(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocTsMst ts = null;
        try {
            tx = session.beginTransaction();
            ts = (MocTsMst) session.get(MocTsMst.class, cid);
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
        return ts;
    }
}
