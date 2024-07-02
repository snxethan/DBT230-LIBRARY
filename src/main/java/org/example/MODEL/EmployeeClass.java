package org.example.MODEL;

public class EmployeeClass {
    // Instance variables
    private int id;
    private String fname;
    private String lname;
    private int hireYear;

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        } else {
            this.id = id;
        }
    }

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        if(fname == null || fname.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        } else {
            this.fname = fname;
        }
    }

    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        if(lname == null || lname.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        } else {
            this.lname = lname;
        }
    }

    public int getHireYear() {
        return hireYear;
    }
    public void setHireYear(int hireYear) {
        if(hireYear < 0) {
            throw new IllegalArgumentException("Hire year cannot be negative");
        } else {
            this.hireYear = hireYear;
        }
    }

    // Constructors
    public EmployeeClass(int id, String fname, String lname, int hireYear) {
        setId(id);
        setFname(fname);
        setLname(lname);
        setHireYear(hireYear);
    }

    /**_
     * Returns the employee data as a string.
     * @return employee data
     */
    public String getEmployee() {
        //return employee data as string builder
        StringBuilder employeeString = new StringBuilder();
        employeeString.append("Employee ID: ").append(id).append("\n");
        employeeString.append("Name: ").append(fname).append(" ").append(lname).append("\n");
        employeeString.append("Hire year: ").append(hireYear).append("\n");
        return employeeString.toString();
    }
}
