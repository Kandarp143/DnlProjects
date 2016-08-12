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
import kp.beans.data.pojo.MocQcMst;
import kp.logic.GeneralMethods;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class QcDao {

    private GeneralMethods gm;

    public static void main(String[] args) {
        MocQcMst qc = new MocQcMst();
        MocInitMst mst = new MocInitMst();
        mst.setCaseId(1);
        qc.setActDate(new GeneralMethods().getSysDate());
        qc.setUserId("02948");
        qc.setK1("TestQC331");
        qc.setK2("TestQC332");
        qc.setMocInitMst(mst);
        qc.setCaseId(1);

        QcDao qd = new QcDao();
//        qd.saveQc(qc);
//        qd.updateQc(qc);
//        qd.deleteQc(1);
        qd.fecthQc(1);
    }

    public int saveQc(MocQcMst bean) {
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
        Logger.getLogger(TsDao.class.getName()).log(Level.SEVERE, "CID : " + bean.getMocInitMst().getCaseId());
        return bean.getMocInitMst().getCaseId();

    }

    public int updateQc(MocQcMst bean) {
        Transaction tx = null;
        Session session = null;
        gm = new GeneralMethods();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            MocQcMst fetchmst = (MocQcMst) session.get(MocQcMst.class, bean.getMocInitMst().getCaseId());
            fetchmst.setK1(bean.getK1());
            fetchmst.setK2(bean.getK2());
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

    public void deleteQc(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MocQcMst fetchmst = (MocQcMst) session.get(MocQcMst.class, cid);
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

    public MocQcMst fecthQc(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        MocQcMst qc = null;
        try {
            tx = session.beginTransaction();
            qc = (MocQcMst) session.get(MocQcMst.class, cid);
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
        return qc;
    }
}
