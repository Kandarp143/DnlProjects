package kp.beans.wf.pojo;
// Generated Nov 10, 2015 7:53:17 AM by Hibernate Tools 4.3.1




/**
 * MocWfAct generated by hbm2java
 */
public class MocWfAct  implements java.io.Serializable {


     private int actId;
     private String actName;

    public MocWfAct() {
    }

	
    public MocWfAct(int actId) {
        this.actId = actId;
    }
    public MocWfAct(int actId, String actName) {
       this.actId = actId;
       this.actName = actName;
    }
   
    public int getActId() {
        return this.actId;
    }
    
    public void setActId(int actId) {
        this.actId = actId;
    }
    public String getActName() {
        return this.actName;
    }
    
    public void setActName(String actName) {
        this.actName = actName;
    }




}


