package ma.ensa.project.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ma.ensa.project.R
import ma.ensa.project.database.UserDatabase
import ma.ensa.project.repository.UserRepository
import ma.ensa.project.utils.UserViewModelFactory
import ma.ensa.project.view.viewmodel.UserViewModel




class RegisterFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = UserDatabase.getInstance(requireContext()).userDao()
        val userRepository = UserRepository.getInstance(userDao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userRepository)).get(UserViewModel::class.java)

        val etFullname = view.findViewById<EditText>(R.id.et_name)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val etRepassword = view.findViewById<EditText>(R.id.et_repassword)
        val btnRegister = view.findViewById<Button>(R.id.btn_register)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.registrationResult.collect { result ->
                    result?.let {
                        when (it.isSuccess) {
                            true -> {
                                // Clear EditTexts
                                etFullname.text.clear()
                                etEmail.text.clear()
                                etPassword.text.clear()
                                etRepassword.text.clear()


                            }
                            false -> {
                                Toast.makeText(requireContext(), "Registration failed. Try again.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val fullname = etFullname.text.toString().trim()
            val repassword = etRepassword.text.toString().trim()

            when {
                fullname.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty() -> {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
                password != repassword -> {
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    userViewModel.registerUser(email, fullname, password)
                }
            }
        }
    }
}
