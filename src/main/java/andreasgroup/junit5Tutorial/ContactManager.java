package andreasgroup.junit5Tutorial;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 21/Jan/2021 to junit5Tutorial
 */
public class ContactManager {
    Map<String, Contact> contactList = new ConcurrentHashMap<String, Contact>();

    public void addContact(String firstName, String lastName, String phoneNumber){
        Contact newContact = new Contact(firstName, lastName, phoneNumber);
        validateContact(newContact);
        checkContactAlreadyExists(newContact);
        contactList.put(generateKey(newContact),newContact);
    }

    public void checkContactAlreadyExists(Contact newContact){
        if(contactList.containsValue(newContact)){
            throw new RuntimeException("Contact Already Exists");
        }
    }

    public Collection<Contact> getAllContacts(){
        return contactList.values();
    }

    public void validateContact(Contact newContact){
        newContact.validateFirstName();
        newContact.validateLastName();
        newContact.validatePhoneNumber();
    }

    public String generateKey(Contact contact){
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }

}
