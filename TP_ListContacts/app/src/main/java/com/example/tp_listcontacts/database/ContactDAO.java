package com.example.tp_listcontacts.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    LiveData<List<ContactEntity>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE phoneNumber = :phoneNumber LIMIT 1")
    ContactEntity getContactByPhone(String phoneNumber);

    @Transaction
    default void insertIfNotExists(ContactEntity contact) {
        ContactEntity existing = getContactByPhone(contact.getPhoneNumber());
        if (existing == null) {
            insert(contact);
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactEntity contact);

    @Update
    void update(ContactEntity contact);

    @Delete
    void delete(ContactEntity contact);

    @Query("DELETE FROM contacts")
    void deleteAll();
}
