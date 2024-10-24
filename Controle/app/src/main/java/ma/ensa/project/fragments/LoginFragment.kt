package ma.ensa.project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ma.ensa.project.SecondActivity
import ma.ensa.project.utils.MyDatabaseHelper
import ma.ensa.project.R

class LoginFragment : Fragment() {
    private lateinit var dbHelper: MyDatabaseHelper // Database helper instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    // This is where you should initialize views and set up listeners
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views by their IDs after the view is created
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)

        // Set up the click listener for the login button
        btnLogin.setOnClickListener {
            // Display a toast for debugging
            Toast.makeText(requireContext(), "Login", Toast.LENGTH_SHORT).show()

            // Get email and password values from EditTexts
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Initialize the database helper
            dbHelper = MyDatabaseHelper(requireContext())

            // Call the login method and get the User object
            val user = dbHelper.loginUser(email, password)

            // Check if the login was successful (assuming a non-null User means success)
            if (user != null) {
                // Create an Intent to start the next activity
                val intent = Intent(requireActivity(), SecondActivity::class.java)

                // (Optional) Pass data to the next activity
                intent.putExtra("USER_ID", user.id)
                intent.putExtra("USER_EMAIL", user.email)

                // Start the next activity
                startActivity(intent)
            } else {
                // Show error message (e.g., login failed)
                Toast.makeText(requireContext(), "Invalid login. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
