package com.example.swimminggo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String username, dob, email, firstName, lastName, phone, roleName;
    private int gender, id;
    private int isVerified;
    private int age;

    public User(){
        username = dob = email = firstName = lastName = phone = "";
    }
    public User(String username, String dob, String email, String firstName, String lastName, String phone, int gender, int id, String roleName, int isVerified) {
        this.username = username;
        this.dob = dob;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.id = id;
        this.roleName = roleName;
        this.isVerified = isVerified;
    }

    public User(JSONObject userObject){
        try {
            this.dob = userObject.getString("dob");
            this.email = userObject.getString("email");
            this.firstName = userObject.getString("first_name");
            this.gender = userObject.getInt("gender");
            this.id = userObject.getInt("id");
            this.isVerified = userObject.getInt("is_verified");
            this.lastName = userObject.getString("last_name");
            this.phone = userObject.getString("phone");
            this.roleName = userObject.getString("role_name");
            this.username = userObject.getString("username");
            this.age = userObject.getInt("age");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getAge() {
        if (dob.equals("null"))
            return 0;
        Date date = new Date(this.dob);
        return date.calculateAge();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getDob() {
        if (dob.equals("null"))
            return "";
        return dob;
    }

    public String getEmail() {
        if (email.equals("null"))
            return "";
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        if (phone.equals("null"))
            return "";
        return phone;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUser(String dob, String email,String firstName, String lastName, String phone, int gender, int id, String roleName, int isVerified ){
        setDob(dob);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setGender(gender);
        setId(id);
        setRoleName(roleName);
        setIsVerified(isVerified);
    }

    public String getFullName(){
        if (lastName.equals("null") || firstName.equals("null"))
            return "Chưa cập nhật";
        return firstName + " " + lastName;

    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public abstract void getDataFromJSONObject(JSONObject jsonObject);
    public abstract JSONObject convertToJSONObject();

}
