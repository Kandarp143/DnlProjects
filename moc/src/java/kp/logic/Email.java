/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.logic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kp.beans.data.pojo.MocInitMst;
import kp.dao.data.MocDao;
import kp.dao.user.UserDao;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 02948
 */
public class Email {

    static final Logger logger = Logger.getLogger(Email.class.getName());

    public static void main(String args[]) throws SQLException, EmailException {
        Email e = new Email();
//        e.Notify(1);
        e.Notify_tsk(1);
//        e.Notify_Approved(1);
    }

    //this method for retrive default body for moc all mail
    public String Default_Body(int cid) {
        String x = "";
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.getNamedQuery("DefaultEmailBody");
            query.setInteger("cid", cid);
            Iterator iterator = query.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                x = "<font face='verdana' size='3' color ='black'>Please Review Details </br></br>"
                        + "</br></br>"
                        + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0' "
                        + " style='table-layout: fixed;word-wrap: break-word;white-space: normal'>"
                        + "<tr><th align='left' width='35%'>Request ID</th><td>" + ((BigDecimal) tuple[0]).toString() + "</td></tr>"
                        + "<tr><th align='left'>Title</th><td>" + (String) tuple[1] + "</td></tr>"
                        + "<tr><th align='left'>Created By</th><td>" + (String) tuple[2] + "</td></tr>"
                        + "<tr><th align='left'>Creation Date</th><td>" + (String) tuple[3] + "</td></tr>"
                        + "<tr><th align='left'>From Stage</th><td>" + (String) tuple[4] + "</td></tr>"
                        + "<tr><th align='left'>To Stage</th><td>" + (String) tuple[5] + "</td></tr>"
                        + "<tr><th align='left'>Last Updated By </th><td>" + (String) tuple[6] + "</td></tr>"
                        + "<tr><th align='left'>Last Updated Date</th><td>" + (String) tuple[7] + "</td></tr>"
                        + "</table>"
                        + "</font>";
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
        return x;
    }

    //method for notify action email at each stage
    public void Notify(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Email em = new Email();
        try {
            tx = session.beginTransaction();
            Query query = session.getNamedQuery("Notify");
            query.setInteger("cid", cid);
            Iterator iterator = query.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                if (!tuple[1].toString().equalsIgnoreCase("")) {
                    em.Sending_Action_mail(cid, tuple[1].toString());
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
    }

    //method for notify task email at create stage ( document to be updated)
    public void Notify_tsk(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Email em = new Email();
        try {
            tx = session.beginTransaction();
            Query query = session.getNamedQuery("TskEmailBody");
            query.setInteger("cid", cid);
            Iterator iterator = query.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                if (!tuple[1].toString().equalsIgnoreCase("")) {
                    em.Sending_Tsk_mail(cid, tuple[0].toString(),
                            tuple[1].toString(), tuple[3].toString(),
                            tuple[4].toString());
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

    }

    //method for send last mail that moc is approved and closed
    public void Notify_Approved(int cid) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Email em = new Email();
        try {
            tx = session.beginTransaction();
            //query will retrive creator,hod,prod head and all eng
            Query query = session.getNamedQuery("LstEmailBody");
            query.setInteger("cid", cid);
            Iterator iterator = query.list().iterator();
            while (iterator.hasNext()) {
                Object[] tuple = (Object[]) iterator.next();
                if (!tuple[1].toString().equalsIgnoreCase("")) {
                    em.Sending_Approved_mail(cid, tuple[1].toString());
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
    }

    //supporting method of Notify method
    public void Sending_Action_mail(int cid, String to) {
        try {
            MultiPartEmail email = new HtmlEmail();
            email.setHostName("200.200.1.1");
            email.setSmtpPort(25);
            email.setFrom("DNL.Moc_Admin@deepaknitrite.com", "MOC@No_Reply");
            email.setSubject("Require Action For MOC_REQ :" + cid);
            String x = Default_Body(cid);
            email.setMsg(x);
            email.addTo(to);
            email.send();
            logger.log(Level.INFO, "Action Mail Sent To :{0}", to);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //supporting method of Notify_tsk method
    public void Sending_Tsk_mail(int cid, String uid, String to, String doc, String date) {
        try {
            MultiPartEmail email = new HtmlEmail();
            email.setHostName("200.200.1.1");
            email.setSmtpPort(25);
            //FROM
            email.setFrom("DNL.Moc_Admin@deepaknitrite.com", "MOC@No_Reply");
            //SUBJECT
            email.setSubject("Task Assigned to you for  MOC_REQ :" + cid);
            //MSG
            String x = Default_Body(cid);
            String mm = "</br></br><font face='verdana' size='3' color ='black'>Task Details </br></br>"
                    + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                    + "<tr><th>Task</th><td>" + doc + "</td></tr>"
                    + "<tr><th>Target Date give by assigner</th><td>" + date + "</td></tr>"
                    + "</table>" + "<br/><br/><br/><br/>" + "<h4>Please Contact  to Creator  of this moc for detail concern"
                    + "</font>";
            x = x + mm;
//            try {
//                // Create the attachment
//                String path = Select.Get_Rpt_Path(cid);
//                EmailAttachment attachment = new EmailAttachment();
//                attachment.setPath(path);
//                attachment.setName("MOC #" + cid + "_Report.pdf");
//                attachment.setDisposition(EmailAttachment.ATTACHMENT);
//                email.attach(attachment);
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "Attachment Exception {0}", e);
//            }
            email.setMsg(x);
            email.addTo(to);
            email.send();
            logger.log(Level.INFO, "Task Mail Sent TO :" + to + "For DOC :" + doc);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //supporting method of Notify_Approved
    public void Sending_Approved_mail(int cid, String to) {
        try {
            MultiPartEmail email = new HtmlEmail();
            email.setHostName("200.200.1.1");
            email.setSmtpPort(25);
            //FROM
            email.setFrom("DNL.Moc_Admin@deepaknitrite.com", "MOC@No_Reply");
            //SUBJECT
            email.setSubject("MOC_REQ #" + cid + " Has been Approved !");
            //MSG
            String x = Default_Body(cid);

            MocInitMst mst = new MocInitMst();
            MocDao mdao = new MocDao();
            mst = mdao.fetchMoc(cid);
            String msg = "<br/><br/><font face='verdana' size='3' color ='black'>"
                    + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                    + "<tr><th>Generated MOC No</th><td><b>" + mst.getMocNo() + "</b>  (YYYY-MON-DD)</td></tr>"
                    + "<tr><th>Approved Date</th><td><b>" + mst.getAppDate() + "</b></td></tr>"
                    + "</table>"
                    + "</font>";

            x = x + msg;
//            try {
            //                // Create the attachment
            //                String path = Select.Get_Rpt_Path(cid);
            //                EmailAttachment attachment = new EmailAttachment();
            //                attachment.setPath(path);
            //                attachment.setName("MOC #" + cid + "_Report.pdf");
            //                attachment.setDisposition(EmailAttachment.ATTACHMENT);
            //                email.attach(attachment);
            //            } catch (Exception e) {
            //                logger.log(Level.SEVERE, "Attachment Exception {0}", e);
            //            }
            email.setMsg(x);
            email.addTo(to);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
