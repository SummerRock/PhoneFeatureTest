package com.example.yanxia.phonefeaturetest.retrofit;

public class Repo {

    /**
     * message : Requires authentication
     * documentation_url : https://developer.github.com/v3/users/#get-the-authenticated-user
     */

    private String message;
    private String documentation_url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }
}
