package com.example.myapplication18;

import java.io.Serializable;
import java.util.Objects;

public class RecyclerViewTest005Activity_TreeData implements Serializable
{

    private static final long serialVersionUID = 1825913344212097269L;
    /**
     * 公司ID
     */
    private String companyID;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司的层级：0,1,2,3...  最上级公司为0。UI需根据公司的层级缩进
     */
    private int companyLevel;
    /**
     * 上级公司的ID
     */
    private String parentCompanyID;
    /**
     * 是否有下级公司(默认没有)，有子公司需展示 展开/收缩 的箭头
     */
    private boolean hasChildCompany;

    /*public RecyclerViewTest005Activity_TreeData(){
        hasChildCompany = false;
    }*/

    public RecyclerViewTest005Activity_TreeData(String companyID, String companyName, int companyLevel, String parentCompanyID, boolean hasChildCompany) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyLevel = companyLevel;
        this.parentCompanyID = parentCompanyID;
        this.hasChildCompany = hasChildCompany;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyLevel()
    {
        return companyLevel;
    }

    public void setCompanyLevel(int companyLevel)
    {
        this.companyLevel = companyLevel;
    }

    public String getParentCompanyID()
    {
        return parentCompanyID;
    }

    public void setParentCompanyID(String parentCompanyID)
    {
        this.parentCompanyID = parentCompanyID;
    }

    public boolean isHasChildCompany()
    {
        return hasChildCompany;
    }

    public void setHasChildCompany(boolean hasChildCompany)
    {
        this.hasChildCompany = hasChildCompany;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecyclerViewTest005Activity_TreeData that = (RecyclerViewTest005Activity_TreeData) o;
        return Objects.equals(companyID, that.companyID);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(companyID);
    }
}