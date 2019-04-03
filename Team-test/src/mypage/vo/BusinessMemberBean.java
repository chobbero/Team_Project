package mypage.vo;

import java.sql.Date;

public class BusinessMemberBean {

    private String user_id;
    private String business_number;
    private String business_name;
    private int store_num;
    private Date business_date;

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBusiness_number() {
        return business_number;
    }

    public void setBusiness_number(String business_number) {
        this.business_number = business_number;
    }

    public int getStore_num() {
        return store_num;
    }

    public void setStore_num(int store_num) {
        this.store_num = store_num;
    }

    public Date getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(Date business_date) {
        this.business_date = business_date;
    }

}
