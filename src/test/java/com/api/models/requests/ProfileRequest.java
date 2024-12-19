package com.api.models.requests;

public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    public ProfileRequest(){

    }

    public ProfileRequest(String firstName, String lastName, String email, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "ProfileRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }


    public static class Builder {

        private String email;
        private String firstName;
        private String lastName;
        private String mobileNumber;



        public ProfileRequest.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;

        }

        public ProfileRequest.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;

        }
        public ProfileRequest.Builder email(String email) {
            this.email = email;
            return this;

        }
        public ProfileRequest.Builder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;

        }

        public ProfileRequest build() { //job of Build method is to create the object of outer class
            ProfileRequest profileRequest = new ProfileRequest(firstName, lastName, email, mobileNumber);
            return profileRequest;
        }

    }
}
