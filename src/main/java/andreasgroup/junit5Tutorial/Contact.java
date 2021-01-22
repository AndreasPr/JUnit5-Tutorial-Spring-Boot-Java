package andreasgroup.junit5Tutorial;

/**
 * Created on 21/Jan/2021 to junit5Tutorial
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void validateFirstName(){
        if(this.firstName.isBlank()){
            throw new RuntimeException("First Name cannot be null or Empty");
        }
    }

    public void validateLastName(){
        if(this.lastName.isBlank()){
            throw new RuntimeException("Last Name cannot be null or Empty");
        }
    }

    public void validatePhoneNumber(){
        if(this.phoneNumber.isBlank()){
            throw new RuntimeException("Phone Number cannot be null or Empty");
        }
        if(this.phoneNumber.length() != 10){
            throw new RuntimeException("Phone Number must include 10 digits");
        }
        if(!this.phoneNumber.startsWith("0")){
            throw new RuntimeException("Phone Number must start with 0");
        }
    }
}
