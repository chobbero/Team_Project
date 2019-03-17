package board.vo;

import java.sql.Date;

public class BoardBean {

    private int board_num;
    private String user_id;
    private int store_num;
    private String board_subject;
    private String board_content;
    private double board_rating;
    private int board_like;
    private Date board_date;
    private int board_readcount;

    public int getBoard_num() {
        return board_num;
    }

    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStore_num() {
        return store_num;
    }

    public void setStore_num(int store_num) {
        this.store_num = store_num;
    }

    public String getBoard_subject() {
        return board_subject;
    }

    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public double getBoard_rating() {
        return board_rating;
    }

    public void setBoard_rating(double board_rating) {
        this.board_rating = board_rating;
    }

    public int getBoard_like() {
        return board_like;
    }

    public void setBoard_like(int board_like) {
        this.board_like = board_like;
    }

    public Date getBoard_date() {
        return board_date;
    }

    public void setBoard_date(Date board_date) {
        this.board_date = board_date;
    }

    public int getBoard_readcount() {
        return board_readcount;
    }

    public void setBoard_readcount(int board_readcount) {
        this.board_readcount = board_readcount;
    }

}
