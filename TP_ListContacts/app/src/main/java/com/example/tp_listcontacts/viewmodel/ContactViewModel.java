package com.example.tp_listcontacts.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tp_listcontacts.database.ContactEntity;
import com.example.tp_listcontacts.repository.ContactRepository;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<ContactEntity>> allContacts;

    public ContactViewModel(Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public LiveData<List<ContactEntity>> getAllContacts() {
        return allContacts;
    }

    public void insert(ContactEntity contact) {
        repository.insert(contact);
    }

    public void update(ContactEntity contact) {
        repository.update(contact);
    }

    public void delete(ContactEntity contact) {
        repository.delete(contact);
    }

    public void loadSystemContacts() {
        repository.loadSystemContacts();
    }

    public void syncContacts() {
        repository.syncContacts();
    }
}
