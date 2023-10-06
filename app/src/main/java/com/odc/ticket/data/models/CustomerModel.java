package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class CustomerModel {

    @SerializedName("customerID")
    private long id;

    @SerializedName("title")
    private long titleId;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("fatherName")
    private String fatherName;

    @SerializedName("motherName")
    private String motherName;

    @SerializedName("phoneNumber1")
    private String phoneNumber1;

    @SerializedName("phoneNumber2")
    private String phoneNumber2;

    @SerializedName("email")
    private String email;

    @SerializedName("whatsappNumber")
    private String whatsappNumber;

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("township")
    private String township;

    @SerializedName("idCardType1")
    private long idCardType1;

    @SerializedName("idCardNumber1")
    private String idCardNumber1;

    @SerializedName("idCardExpiryDate1")
    private String idCardExpiryDate1;

    @SerializedName("signature")
    private String signature;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public long getIdCardType1() {
        return idCardType1;
    }

    public void setIdCardType1(long idCardType1) {
        this.idCardType1 = idCardType1;
    }

    public String getIdCardNumber1() {
        return idCardNumber1;
    }

    public void setIdCardNumber1(String idCardNumber1) {
        this.idCardNumber1 = idCardNumber1;
    }

    public String getIdCardExpiryDate1() {
        return idCardExpiryDate1;
    }

    public void setIdCardExpiryDate1(String idCardExpiryDate1) {
        this.idCardExpiryDate1 = idCardExpiryDate1;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }
}
