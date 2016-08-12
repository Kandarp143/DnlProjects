/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.LoginBean;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface LoginInter {

    //to authenticate user login id and password
    public String authenticateUser(LoginBean bean);

 

    //to get user id from login id

    public String getUserName(LoginBean bean);

    //to add user's login history
    public void addLoginHistory(LoginBean bean);

    //to update user's login history
    public void updateLoginHistory(LoginBean bean);

    //to get login history 
    public ArrayList<LoginBean> getLoginHistory();

    //to get login history 
    public ArrayList<LoginBean> getLoginHistory(String user_id);
    
     //to get user by role
    public ArrayList<LoginBean> getUserByRole(String role);



}
