package ma.ensa.project.view.fragments

import android.content.Intent
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
import ma.ensa.project.SecondActivity

class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        val userDao = UserDatabase.getInstance(requireContext()).userDao()
        val userRepository = UserRepository.getInstance(userDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        )[UserViewModel::class.java]

        // Find views by ID
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.loginResult.collect { result ->
                    result?.let {
                        when (it.isSuccess) {
                            true -> {
                                showToast("Login Success")
                                clearInputs()
                                navigateToActivity()


                            }
                            false -> {
                                showToast("Login failed")
                            }
                        }
                    }
                }
            }
        }
        // Setup login button click listener
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                email.isEmpty() || password.isEmpty() -> {
                    showToast("Please fill all fields")
                }
                else -> {
                    userViewModel.loginUser(email, password)
                }
            }
        }
    }

    private fun navigateToActivity() {
        val intent = Intent(requireContext(), SecondActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun clearInputs() {
        view?.findViewById<EditText>(R.id.et_email)?.text?.clear()
        view?.findViewById<EditText>(R.id.et_password)?.text?.clear()
    }
}