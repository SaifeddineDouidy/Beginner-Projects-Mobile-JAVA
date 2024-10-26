package com.example.tp_listcontacts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_listcontacts.database.ContactEntity;
import com.example.tp_listcontacts.utils.ContactPermissionHelper;
import com.example.tp_listcontacts.utils.ContactViewModelFactory;
import com.example.tp_listcontacts.view.adapters.ContactAdapter;
import com.example.tp_listcontacts.viewmodel.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactClickListener {
    private ContactViewModel contactViewModel;
    private ContactAdapter adapter;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private boolean contactsLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser le ViewModel
        ContactViewModelFactory factory = new ContactViewModelFactory(getApplication());
        contactViewModel = new ViewModelProvider(this, factory).get(ContactViewModel.class);

        // Configurer le RecyclerView
        setupRecyclerView();

        // Configurer le FAB
        FloatingActionButton fabAddContact = findViewById(R.id.fabAddContact);
        fabAddContact.setOnClickListener(v -> handleAddContact());

        // Vérifier et demander les permissions
        checkPermissions();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter();
        adapter.setOnContactClickListener(this);
        recyclerView.setAdapter(adapter);

        // Observer les changements de contacts
        contactViewModel.getAllContacts().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });
    }

    private void loadContacts() {
        contactViewModel.loadSystemContacts();
    }

    private void checkPermissions() {
        if (!ContactPermissionHelper.hasContactPermission(this)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        } else if (!contactsLoaded) {
            loadContacts();
            contactsLoaded = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void handleAddContact() {
        Toast.makeText(this, "Add Contact clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactClick(ContactEntity contact) {
        Toast.makeText(this, "Contact clicked: " + contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactLongClick(ContactEntity contact) {
        Toast.makeText(this, "Long press on: " + contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            } else {
                Toast.makeText(this, "Permission denied to read contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
