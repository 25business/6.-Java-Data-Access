package com.example.ui;

import com.example.dao.ContactDAO;
import com.example.models.Contact;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.document.TableRowStyle;
import org.beryx.textio.TextIO;

import java.util.List;

public class ContactsUI {

    public static String view_all() {
        List<Contact> contacts = ContactDAO.all();
        AsciiTable table = new AsciiTable();
        table.addRule();
        if(contacts.size() == 0) {
            table.addRow("There are no contact available.");
            table.addRule();
        } else {
            table.addRow("ID", "First name", "Last name",
                    "Email", "Phone (home)", "Phone (work)");
            table.addRule();
            for(var contact : contacts) {
                table.addRow(
                    contact.getContacts_id(),
                    contact.getFirst_name(),
                    contact.getLast_name(),
                    contact.getEmail() == null ? "-" : contact.getEmail(),
                    contact.getPhone_home() == null ? "-" : contact.getPhone_home(),
                    contact.getPhone_work() == null ? "-" : contact.getPhone_work()
                );
                table.addRule();
            }
        }

        return table.render();
    }

    public static String new_contact(TextIO input) {
        Contact new_contact = new Contact();
        new_contact.setFirst_name( input.newStringInputReader().withMinLength(1).read("First name:"));
        new_contact.setLast_name( input.newStringInputReader().withMinLength(1).read("Last name:"));
        String email = input.newStringInputReader().withMinLength(0).read("E-mail (Enter to skip)");
        String phone_home = input.newStringInputReader().withMinLength(0).read("Home phone (Enter to skip):");
        String work_phone = input.newStringInputReader().withMinLength(0).read("Work phone (Enter to skip):");
        if(email.trim().length() != 0) new_contact.setEmail(email.trim());
        if(phone_home.trim().length() != 0) new_contact.setEmail(phone_home.trim());
        if (work_phone.trim().length() != 0) new_contact.setEmail(work_phone.trim());
        int new_id = ContactDAO.insert(new_contact);
        return new_id > 0 ? "Contact has been saved." :
                "Error adding new contact.";
    }

    public static String update(TextIO input) {
        int contacts_id = input.newIntInputReader().read("Enter contact ID:");
        Contact contact = ContactDAO.one(contacts_id);
        input.getTextTerminal().println("To ignore field, enter - .");
        String first_name = input.newStringInputReader().read("First name (" +
                contact.getFirst_name() + "):");
        String last_name = input.newStringInputReader().read("Last name (" +
                contact.getLast_name() + "):");
        String email = input.newStringInputReader().read("E-mail (" +
         contact.getEmail() + ":");
        String phone_home = input.newStringInputReader().read("Phone home (" +
                contact.getPhone_home() + ":");
        String phone_work = input.newStringInputReader().read("Phone work (" +
                contact.getPhone_work() + ":");

        if(!first_name.equals("-")) contact.setFirst_name(first_name);
        if(!last_name.equals("-")) contact.setLast_name(last_name);
        if(!email.equals("-")) contact.setEmail(email);
        if(!phone_home.equals("-")) contact.setPhone_home(phone_home);
        if(!phone_work.equals("-")) contact.setPhone_work(phone_work);

        int result = ContactDAO.update(contact);
        return result > 0 ? "Contact has been updated." : "Error updating contact!";
    }
}
