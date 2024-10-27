package ma.ensa.projet.view.fragments

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
import ma.ensa.projet.R
import ma.ensa.projet.view.viewmodel.UserViewModel

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

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        val etFullname = view.findViewById<EditText>(R.id.et_name)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val etRepassword = view.findViewById<EditText>(R.id.et_repassword)
        val btnRegister = view.findViewById<Button>(R.id.btn_register)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.registrationResult.collect { result ->
                    result?.let {
                        if (it.isSuccess) {
                            etFullname.text.clear()
                            etEmail.text.clear()
                            etPassword.text.clear()
                            etRepassword.text.clear()
                            showToast("Registration successful")
                        } else {
                            showToast("Registration failed. Try again.")
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
                    showToast("Please fill all fields")
                }
                password != repassword -> {
                    showToast("Passwords do not match")
                }
                else -> {
                    userViewModel.registerUser(email, fullname, password)
                }
            }
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
