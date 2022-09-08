package com.example.dao;

import com.example.models.Contact;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.util.List;

public class ContactDAO {
    private static Jdbi jdbi = null;

    private static Jdbi get_connection() {
        if(jdbi == null)
            jdbi = Jdbi.create("jdbc:mysql://localhost:3307/da3_contacts","jadmin","1234");
        return jdbi;
    }

    public static List<Contact> all() {
        Jdbi jdbi = get_connection();
        return jdbi.withHandle(handle -> {
            Query select_contacts_query = handle.createQuery("SELECT * FROM contacts;");
            return select_contacts_query.mapToBean(Contact.class).list();
        });
    }

    public static int insert(Contact new_contact) {
        Jdbi jdbi = get_connection();
        return jdbi.withHandle(handle -> {
            Update insert_new_contact = handle.createUpdate("""
                    INSERT INTO contacts VALUES (NULL,:first_name,:last_name,
                     :email,:phone_work,:phone_home)""");
            return insert_new_contact.bindBean(new_contact).execute();
        });
    }
    public static Contact one(int contact_id) {
        Jdbi jdbi = get_connection();
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM contacts WHERE contacts_id = :contact_id")
                    .bind("contact_id", contact_id)
                    .mapToBean(Contact.class).first();
        });
    }
    public static int update(Contact contact) {
        Jdbi jdbi = get_connection();
        return jdbi.withHandle(handle -> {
            return handle.createUpdate("""
                            UPDATE contacts SET
                            first_name = :first_name,
                            last_name = :last_name,
                            email = :email,
                            phone_home = :phone_home,
                            phone_work = :phone_work
                            WHERE contacts_id = :contacts_id
                            ;""")
                    .bindBean(contact).execute();
        });
    }
}
