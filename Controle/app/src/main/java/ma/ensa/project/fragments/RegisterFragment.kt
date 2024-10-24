package ma.ensa.project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ma.ensa.project.utils.MyDatabaseHelper // Import your database helper
import ma.ensa.project.R

class RegisterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dbHelper: MyDatabaseHelper // Database helper instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val et_fullname = view.findViewById<EditText>(R.id.et_name)
        val et_email = view.findViewById<EditText>(R.id.et_email)
        val et_password = view.findViewById<EditText>(R.id.et_password)
        val btn_register = view.findViewById<Button>(R.id.btn_register)
        val et_repassword = view.findViewById<EditText>(R.id.et_repassword)

        // Initialize the database helper
        dbHelper = MyDatabaseHelper(requireContext())

        btn_register.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            val fullname = et_fullname.text.toString().trim()
            val repassword = et_repassword.text.toString().trim()

            // Validate inputs
            if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != repassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Attempt to register the user in the database
                val result = dbHelper.registerUser(fullname, email, password)
                if (result) {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    // Optionally, navigate to another fragment or activity
                    et_fullname.text.clear()
                    et_email.text.clear()
                    et_password.text.clear()
                    et_repassword.text.clear()
                } else {
                    Toast.makeText(requireContext(), "Registration failed. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}
