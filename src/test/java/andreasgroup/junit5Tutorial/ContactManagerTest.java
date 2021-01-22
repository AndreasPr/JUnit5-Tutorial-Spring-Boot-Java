package andreasgroup.junit5Tutorial;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 21/Jan/2021 to junit5Tutorial
 */

class ContactManagerTest {

    ContactManager contactManager;
    @BeforeAll
    public static void setupAll(){
        System.out.println("Should be called before all tests");
    }

    @BeforeEach
    void setUp(){
        contactManager = new ContactManager();
    }

    @Test
    void addContact() {
        contactManager.addContact("Andreas", "Priftis", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
        .filter(contact -> contact.getFirstName().equals("Andreas") && contact.getLastName().equals("Priftis") &&
                contact.getPhoneNumber().equals("0123456789")).findAny().isPresent());
    }

    @Test
    @DisplayName("First Name should not be null in a contact addition")
    void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Priftis", "0123456789");
        });
    }

    @Test
    @DisplayName("Last Name should not be null in a contact addition")
    void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Andreas", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Phone Number should not be null in a contact addition")
    void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Andreas", "Priftis", null);
        });
    }

    @AfterEach
    void tearDown(){
        System.out.println("Should be called after each test");
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("Should be called at the end of the Test process");
    }

    //Conditional Executions
    @Test
    @DisplayName("Should not create a contact in MAC OS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Disabled on MAC")
    void shouldNotCreateContactOnWindows(){
        contactManager.addContact("Andreas", "Priftis", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
        .filter(contact -> contact.getFirstName().equals("Andreas") && contact.getLastName().equals("Priftis") &&
                contact.getPhoneNumber().equals("0123456789")).findAny().isPresent());
    }

    //Assumptions
    @Test
    @DisplayName("Contact Creation in DEV Machine")
    void shouldTestContactCreationOnDev(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Andreas", "Priftis", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }





    @Nested
    class RepeatedNestedTest{
        //Repeated Tests
        @DisplayName("Repeat the creation of the contact 5 times")
        @RepeatedTest(value = 5, name = "Repeating contact creation Test {currentRepetition} of {totalRepetitions}")
        void shouldCreateContactsRepeatedly(){
            contactManager.addContact("Andreas", "Priftis", "0123456789");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @Nested
    class ParameterizedNestedTest{
        //Parameterized Tests
        @DisplayName("Parameterized Test")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        void shouldCreateContactBasedOnParameters(String phoneNumber){
            contactManager.addContact("Andreas", "Priftis", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        //Second option of Parameterized Tests implementation is the @MethodSource
//        @DisplayName("Parameterized test with MethodSource")
//        @ParameterizedTest
//        @MethodSource(value = "phoneNumberList")
//        void shouldCreateContactBasedOnMethodSource(String phoneNumber){
//            contactManager.addContact("Andreas", "Priftis", phoneNumber);
//            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
//            Assertions.assertEquals(1, contactManager.getAllContacts().size());
//        }
//
//         List<String> phoneNumberList(){
//            return Arrays.asList("0123456789", "0123456789", "0123456789");
//        }

        //Third option of Parameterized Tests implementation is the @CsvSource
        @DisplayName("Parameterized Test with CsvSource")
        @ParameterizedTest
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        void shouldCreateContactBasedOnCvsSource(String phoneNumber){
            contactManager.addContact("Andreas", "Priftis", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        //Forth option of Parameterized Tests implementation is the @CsvFileSource
        @DisplayName("Parameterized Test with CsvFileSource")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        void shouldCreateContactBasedOnCsvFileSource(String phoneNumber){
            contactManager.addContact("Andreas", "Priftis", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }



}
