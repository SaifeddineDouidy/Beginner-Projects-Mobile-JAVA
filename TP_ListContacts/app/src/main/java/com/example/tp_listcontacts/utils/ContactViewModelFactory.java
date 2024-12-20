package com.example.tp_listcontacts.utils;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_listcontacts.viewmodel.ContactViewModel;

public class ContactViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public ContactViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ContactViewModel.class)) {
            return (T) new ContactViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

