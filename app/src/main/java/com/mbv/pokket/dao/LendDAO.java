package com.mbv.pokket.dao;

/**
 * Created by arindamnath on 13/01/16.
 */
public class LendDAO {

    private String name;
    private float amount;
    private String tenure;
    private String postTime;

    public LendDAO() {

    }

    public LendDAO(String name, float amount, String tenure, String postTime) {
        this.name = name;
        this.amount = amount;
        this.tenure = tenure;
        this.postTime = postTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
}
