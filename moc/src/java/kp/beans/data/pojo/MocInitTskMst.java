package kp.beans.data.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1




/**
 * MocInitTskMst generated by hbm2java
 */
public class MocInitTskMst  implements java.io.Serializable {


     private int docId;
     private String docTitle;

    public MocInitTskMst() {
    }

	
    public MocInitTskMst(int docId) {
        this.docId = docId;
    }
    public MocInitTskMst(int docId, String docTitle) {
       this.docId = docId;
       this.docTitle = docTitle;
    }
   
    public int getDocId() {
        return this.docId;
    }
    
    public void setDocId(int docId) {
        this.docId = docId;
    }
    public String getDocTitle() {
        return this.docTitle;
    }
    
    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }




}


