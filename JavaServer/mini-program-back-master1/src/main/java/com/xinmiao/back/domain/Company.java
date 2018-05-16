package com.xinmiao.back.domain;

import javax.persistence.*;

public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_tags")
    private String companyTags;

    @Column(name = "book_img")
    private String bookImg;

    @Column(name = "company_ceo")
    private String companyCeo;

    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "company_type")
    private Integer companyType;


    @Column(name = "user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return company_id
     */


    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * @return company_description
     */
    public String getCompanyDescription() {
        return companyDescription;
    }

    /**
     * @param companyDescription
     */
    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription == null ? null : companyDescription.trim();
    }

    /**
     * @return company_address
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * @param companyAddress
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * @return company_tags
     */
    public String getCompanyTags() {
        return companyTags;
    }

    /**
     * @param companyTags
     */
    public void setCompanyTags(String companyTags) {
        this.companyTags = companyTags == null ? null : companyTags.trim();
    }

    /**
     * @return book_img
     */
    public String getBookImg() {
        return bookImg;
    }

    /**
     * @param bookImg
     */
    public void setBookImg(String bookImg) {
        this.bookImg = bookImg == null ? null : bookImg.trim();
    }

    /**
     * @return company_ceo
     */
    public String getCompanyCeo() {
        return companyCeo;
    }

    /**
     * @param companyCeo
     */
    public void setCompanyCeo(String companyCeo) {
        this.companyCeo = companyCeo == null ? null : companyCeo.trim();
    }

    /**
     * @return company_url
     */
    public String getCompanyUrl() {
        return companyUrl;
    }

    /**
     * @param companyUrl
     */
    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    /**
     * @return company_type
     */
    public Integer getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType
     */
    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }
}