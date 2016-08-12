/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.WorkBean;
import Dao.BillDao;
import Dao.WorkDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author 02948
 */
public class Email {

    static final Logger logger = Logger.getLogger(Email.class.getName());

    public static void main(String[] args) throws EmailException, SQLException {
        Email e = new Email();
     //   e.Send_Alert_BILL("2", "00090");
      //  e.Send_Alert_AP("2", "00090", "1", "1");
    }

  
    public String Default_Body_PO(String pono) throws SQLException {
        WorkBean wo1 = new WorkBean();
        WorkDao wdao1 = new WorkDao();
        String x = "";
        wo1 = wdao1.getWO(pono);
        x = "<font face='verdana' size='3' color ='black'>Please Review Details </br></br>"
                + "</br></br>"
                + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                + "<tr><th align='left' width='35%'>WO Number</th><td>" + wo1.getPO_NO() + "</td></tr>"
                + "<tr><th align='left'>WO Title</th><td>" + wo1.getPO_TITLE() + "</td></tr>"
                + "<tr><th align='left'>Status</th><td>" + wo1.getSTATUS() + "</td></tr>"
                + "<tr><th align='left'>Created By</th><td>" + wo1.getUSER_NAME() + "</td></tr>"
                + "<tr><th align='left'>Supplier</th><td>" + wo1.getSUP_NAME() + "</td></tr>"
                + "<tr><th align='left'>Site</th><td>" + wo1.getSITE() + "</td></tr>"
                + "<tr><th align='left'>Operating Unit</th><td>" + wo1.getOU() + "</td></tr>"
                + "<tr><th align='left'>Creation Date</th><td>" + wo1.getCR_DATE() + "</td></tr>"
                + "<tr><th align='left'>Currency</th><td>" + wo1.getCUR() + "</td></tr>"
                + "<tr><th align='left'>Retention Amount</th><td>" + wo1.getRET_AMT() + "</td></tr>"
                + "<tr><th align='left'>Type</th><td>" + wo1.getTYPE() + "</td></tr>"
                + "<tr><th align='left'>Expire Date</th><td>" + wo1.getEXP_DATE() + "</td></tr>"
                + "<tr><th align='left'>Total Val</th><td>" + wo1.getVAL() + "</td></tr>"
                + "<tr><th align='left'>Applied tax</th><td>" + wo1.getTOTAL_TAX() + "</td></tr>"
                + "<tr><th align='left'>Total Amount</th><td>" + wo1.getTOTAL_VAL() + "</td></tr>"
                + "</table>"
                + "</font>";

        return x;
    }

    public void Send_Alert(String pono, String nuid) throws EmailException, SQLException {
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("200.200.1.1");
        email.setSmtpPort(25);
        email.setFrom("DNL.CBA_Admin@deepaknitrite.com", "CBA@No_Reply");
        email.setSubject("Require Action For Work Order " + pono);
        String x = Default_Body_PO(pono);
        email.setMsg(x);
        GetMethod g = new GetMethod();
        String to = g.Get_Perameter(nuid, "EMAIL_ID", "CBA_USER_MST");
        email.addTo(to);
        email.send();
        logger.log(Level.INFO, "Action Mail Sent To :{0}", to);
    }

    public String Default_Body_BILL(String bill) throws SQLException {
        WorkBean wo1 = new WorkBean();
        BillDao wdao1 = new BillDao();
        String x = "";
        wo1 = wdao1.getBill(bill);
        x = "<font face='verdana' size='3' color ='black'>Please Review Details </br></br>"
                + "</br></br>"
                + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                + "<tr><th align='left' width='35%'> Bill Number</th><td>" + wo1.getBILL_NO() + "</td></tr>"
                + "<tr><th align='left' width='35%'> Number</th><td>" + wo1.getPO_NO() + "</td></tr>"
                + "<tr><th align='left'>WO Title</th><td>" + wo1.getPO_TITLE() + "</td></tr>"
                + "<tr><th align='left'>Status</th><td>" + wo1.getSTATUS() + "</td></tr>"
                + "<tr><th align='left'>Created By</th><td>" + wo1.getUSER_NAME() + "</td></tr>"
                + "<tr><th align='left'>Supplier</th><td>" + wo1.getSUP_NAME() + "</td></tr>"
                + "<tr><th align='left'>Site</th><td>" + wo1.getSITE() + "</td></tr>"
                + "<tr><th align='left'>Operating Unit</th><td>" + wo1.getOU() + "</td></tr>"
                + "<tr><th align='left'>Creation Date</th><td>" + wo1.getCR_DATE() + "</td></tr>"
                + "<tr><th align='left'>Currency</th><td>" + wo1.getCUR() + "</td></tr>"
                + "<tr><th align='left'>Retention Amount</th><td>" + wo1.getRET_AMT() + "</td></tr>"
                + "<tr><th align='left'>Total Val</th><td>" + wo1.getVAL() + "</td></tr>"
                + "<tr><th align='left'>Applied tax</th><td>" + wo1.getTOTAL_TAX() + "</td></tr>"
                + "<tr><th align='left'>Total Amount</th><td>" + wo1.getTOTAL_VAL() + "</td></tr>"
                + "</table>"
                + "</font>";

        return x;
    }

    public void Send_Alert_BILL(String bill, String nuid) throws EmailException, SQLException {
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("200.200.1.1");
        email.setSmtpPort(25);
        email.setFrom("DNL.CBA_Admin@deepaknitrite.com", "CBA@No_Reply");
        email.setSubject("Require Action For Bill_Number " + bill);
        String x = Default_Body_BILL(bill);
        email.setMsg(x);
        GetMethod g = new GetMethod();
        String to = g.Get_Perameter(nuid, "EMAIL_ID", "CBA_USER_MST");
        email.addTo(to);
        email.send();
        logger.log(Level.INFO, "Action Mail Sent To :{0}", to);
    }

    public String Default_Body_AP(String bill, String req_id, String ino) throws SQLException {
        WorkBean wo1 = new WorkBean();
        BillDao wdao1 = new BillDao();
        String x = "";
        wo1 = wdao1.getBill(bill);
        x = "<font face='verdana' size='3' color ='black'>Please Review Details </br></br>"
                + "</br></br>"
                + "<table width='70%' BORDER='1' CELLPADDING='3' cellspacing='0'>"
                + "<tr><th align='left' width='35%'> Bill Number</th><td>" + wo1.getBILL_NO() + "</td></tr>"
                + "<tr><th align='left' width='35%'> WO Number</th><td>" + wo1.getPO_NO() + "</td></tr>"
                + "<tr><th align='left'>ERP Request ID</th><td>" + req_id + "</td></tr>"
                + "<tr><th align='left'>Invoice NO</th><td>" + ino + "</td></tr>"
                + "<tr><th align='left'>Supplier</th><td>" + wo1.getSUP_NAME() + "</td></tr>"
                + "<tr><th align='left'>Total Amount</th><td>" + wo1.getTOTAL_VAL() + "</td></tr>"
                + "</table>"
                + "</font>";

        return x;
    }

    public void Send_Alert_AP(String bill, String nuid, String req_id, String ino) throws EmailException, SQLException {
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("200.200.1.1");
        email.setSmtpPort(25);
        email.setFrom("DNL.CBA_Admin@deepaknitrite.com", "CBA@No_Reply");
        email.setSubject("AP inv created in ERP For Bill_Number " + bill);
        String x = Default_Body_AP(bill, req_id, ino);
        email.setMsg(x);
        GetMethod g = new GetMethod();
        String to = g.Get_Perameter(nuid, "EMAIL_ID", "CBA_USER_MST");
        email.addTo(to);
        email.send();
        logger.log(Level.INFO, "AP Mail Sent To :{0}", to);
    }

}
