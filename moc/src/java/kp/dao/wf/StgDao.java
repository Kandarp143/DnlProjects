/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.wf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocTsMst;
import kp.dao.data.TsDao;
import kp.dao.user.UserDao;
import kp.logic.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class StgDao {

    public static void main(String[] args) {
        StgDao d = new StgDao();
        d.getNxtStg(1, 0, 1);
    }

    //fetch next stage based on current stage and action done by user for spacific moc
    public ArrayList<Integer> getNxtStg(int stg_c, int act, int cid) {
        ArrayList<Integer> out = new ArrayList<>();
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getNxtStg");
            queryObj.setInteger("stg_c", stg_c);
            queryObj.setInteger("act", act);
            List<BigDecimal> result = queryObj.list();
            result.stream().forEach((r) -> {
                out.add(r.intValue());
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
        out.stream().forEach((i) -> {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "/n getNxtStg : " + i);
        });
        //TS Verification  - IS TS VP required or not
        if (out.get(0) == 18) {
            TsDao tsdao = new TsDao();
            MocTsMst ts = tsdao.fecthTs(cid);
            if (!ts.getI4().equals("true")) {
                out.clear();
                StgDao sdao = new StgDao();
                //again call method
                ArrayList<Integer> tout = sdao.getNxtStg(18, act, cid);
                tout.stream().forEach((i) -> {
                    out.add(i);
                });//                out.add(19);
            }
        }

        return out;
    }

    //fetch stage name from stage id
    public String getStgName(int sid) {
        String out = "";
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query queryObj = session.getNamedQuery("getStgName");
            queryObj.setInteger("sid", sid);
            out = queryObj.uniqueResult().toString();
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

}
