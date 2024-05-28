package model;

public class UserModel {

    private int id;
    private String username;
    private String fname;
    private String lname;
    private String mobile;
    private String role;

   

//    public UserModel(int id, String fname, String lname, String username, String mobile, String role){
//        this.id = id;
//        this.fname = fname;
//        this.lname = lname;
//        this.username = username;
//        this.mobile = mobile;
//        this.role = role;
//    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param usrename the username to set
     */
    public void setUsername(String usrename) {
        this.username = usrename;
    }

    /**
     * @return the f-name
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the f-name to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the l-name
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the l-name to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
