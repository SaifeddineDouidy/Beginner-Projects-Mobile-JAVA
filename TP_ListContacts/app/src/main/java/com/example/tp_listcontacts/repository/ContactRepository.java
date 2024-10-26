package com.example.tp_listcontacts.repository;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import androidx.lifecycle.LiveData;
import com.example.tp_listcontacts.database.ContactDAO;
import com.example.tp_listcontacts.database.ContactDatabase;
import com.example.tp_listcontacts.database.ContactEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private ContactDAO contactDao;
    private LiveData<List<ContactEntity>> allContacts;
    private ExecutorService executorService;
    private Context context;
    private Set<String> processedPhoneNumbers;


    public ContactRepository(Context context) {
        ContactDatabase database = ContactDatabase.getInstance(context);
        this.contactDao = database.contactDao();
        this.allContacts = contactDao.getAllContacts();
        this.executorService = Executors.newSingleThreadExecutor();
        this.context = context;
    }

    // Obtenir tous les contacts de la base de données locale
    public LiveData<List<ContactEntity>> getAllContacts() {
        return allContacts;
    }

    // Insérer un contact dans la base de données locale
    public void insert(ContactEntity contact) {
        executorService.execute(() -> contactDao.insert(contact));
    }

    // Mettre à jour un contact
    public void update(ContactEntity contact) {
        executorService.execute(() -> contactDao.update(contact));
    }

    // Supprimer un contact
    public void delete(ContactEntity contact) {
        executorService.execute(() -> contactDao.delete(contact));
    }

    // Charger les contacts du système
    public void loadSystemContacts() {
        executorService.execute(() -> {
            // Vider d'abord la base de données
            contactDao.deleteAll();

            Set<String> processedNumbers = new HashSet<>();
            ContentResolver contentResolver = context.getContentResolver();

            Cursor cursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            );

            if (cursor != null) {
                try {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String phoneNumber = cursor.getString(
                                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        );

                        // Normaliser le numéro de téléphone (enlever espaces, tirets, etc.)
                        phoneNumber = phoneNumber.replaceAll("[\\s-()]", "");

                        // Vérifier si ce numéro a déjà été traité
                        if (!processedNumbers.contains(phoneNumber)) {
                            @SuppressLint("Range") String name = cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                            );
                            @SuppressLint("Range") String photoUri = cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
                            );

                            ContactEntity contact = new ContactEntity(name, phoneNumber);
                            contactDao.insert(contact);

                            // Marquer ce numéro comme traité
                            processedNumbers.add(phoneNumber);
                        }
                    }
                } finally {
                    cursor.close();
                }
            }
        });
    }
    private boolean contactExists(String phoneNumber) {
        return contactDao.getContactByPhone(phoneNumber) != null;
    }
    // Méthode pour synchroniser les contacts
    public void syncContacts() {
        executorService.execute(() -> {
            // Supprimer tous les contacts existants
            contactDao.deleteAll();
            // Recharger les contacts du système
            loadSystemContacts();
        });
    }
}
