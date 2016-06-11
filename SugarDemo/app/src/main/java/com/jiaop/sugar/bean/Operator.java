package com.jiaop.sugar.bean;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by jiaop on 2016/6/11.
 */
public class Operator extends SugarRecord implements Serializable {

    String userName;
    String gender;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
