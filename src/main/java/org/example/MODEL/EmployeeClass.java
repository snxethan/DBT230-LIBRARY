package org.example.MODEL;

public class EmployeeClass {
    // Instance variables
    private int id;
    private String fName;
    private String lName;
    private int hireYear;

    //region Getters and setters
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

    public String getFName() {
        return fName;
    }
    public void setFName(String fName) {
        if(fName == null || fName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        } else {
            this.fName = fName;
        }
    }

    public String getLName() {
        return lName;
    }
    public void setLName(String lName) {
        if(lName == null || lName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        } else {
            this.lName = lName;
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
    //endregion
    // Constructors
    public EmployeeClass(int id, String fnName, String lName, int hireYear) {
        setId(id);
        setFName(fnName);
        setLName(lName);
        setHireYear(hireYear);
    }

    /**_
     * Returns the employee data as a string.
     * @return employee data
     */
    @Override
    public String toString() {
        //return employee data as string builder
        StringBuilder employeeString = new StringBuilder();
        employeeString.append("Employee ID: ").append(id).append("\n");
        employeeString.append("Name: ").append(fName).append(" ").append(lName).append("\n");
        employeeString.append("Hire year: ").append(hireYear).append("\n");
        return employeeString.toString();
    }
}
