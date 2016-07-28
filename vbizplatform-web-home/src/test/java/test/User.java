/**
  * Copyright (c) 2004-2016 All Rights Reserved.
 */
package test;

import java.util.Date;

/**
 * 
 * @author yuanfeng
 * @version $Id: User.java, v 0.1 2016年4月23日 下午4:04:40 yuanfeng Exp $
 */
public class User {

    private String name;

    private int    age;

    private Date   viStartTime;

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     * 
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     * 
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>viStartTime</tt>.
     * 
     * @return property value of viStartTime
     */
    public Date getViStartTime() {
        return viStartTime;
    }

    /**
     * Setter method for property <tt>viStartTime</tt>.
     * 
     * @param viStartTime value to be assigned to property viStartTime
     */
    public void setViStartTime(Date viStartTime) {
        this.viStartTime = viStartTime;
    }

}
