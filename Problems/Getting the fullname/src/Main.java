class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        if (firstName == null) {
            this.firstName = "";
        } else {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        // write your code here
        if (lastName == null) {
            this.lastName = "";
        } else {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if (this.firstName.length() == 0 && this.lastName.length() > 0) {
            return this.lastName;
        } else if (this.firstName.length() > 0 && this.lastName.length() == 0) {
            return this.firstName;
        } else if (this.firstName.length() > 0 && this.lastName.length() > 0) {
            return this.firstName + " " + this.lastName;
        }
        return "Unknown";
    }
}