package org.example.MODEL;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeClass implements Serializable {
    //region VARS
    private int id;
    private String fName;
    private String lName;
    private int hireYear;
    //endregion

    //region GETTERS & SETTERS
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
        } else if (hireYear > 2024) {
            ;this.hireYear = 2024;
        } else {
            this.hireYear = hireYear;
        }
    }
    //endregion

    //region CONSTRUCTOR
    public EmployeeClass(int id, String fnName, String lName, int hireYear) {
        setId(id);
        setFName(fnName);
        setLName(lName);
        setHireYear(hireYear);
    }
    //endregion

    //region OVERRIDE METHODS
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

    //override methods to allow comparing file objects and prevents duplicate array employee objects from being added.
    @Override // Compares the object with the specified object for order
    public boolean equals(Object obj) {
        if (this == obj) return true; // If the object is compared with itself then return true
        if (obj == null || getClass() != obj.getClass()) return false; // Check if obj is an instance of EmployeeClass
        EmployeeClass employee = (EmployeeClass) obj; // Typecast obj to EmployeeClass so that we can compare data members
        return getId() == employee.getId(); // Assuming eID is the field name for employee ID
    }

    @Override // Returns the hash code value for the object on which this method is invoked
    public int hashCode() { // Returns the hash code value for the object on which this method is invoked
        return Objects.hash(getId()); // Requires import java.util.Objects;
    }
    //endregion
}
