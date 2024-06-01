package com.example.lab10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab10.databinding.ActivityLoginBinding
import com.example.lab10.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        db = Firebase.firestore

        binding.createBtn.setOnClickListener {
            createUser(binding.emailSignupId.text.toString(), binding.passwordSignupId.text.toString())
        }



//        enableEdgeToEdge()
//        setContentView(R.layout.activity_signup)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    newUser()
                }
                else {
                    Snackbar.make(
                        binding.root,
                        "Enter a valid username and password (6 characters)",
                    Snackbar.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun newUser(){
        val user = hashMapOf(
            "name" to binding.nameId.text.toString().trim(),
            "city" to binding.cityId.text.toString().trim(),
            "country" to binding.countryId.text.toString().trim(),
            "phone" to binding.phoneId.text.toString().trim(),
        )

        db.collection("customer")
            .add(user)
            .addOnSuccessListener {
                documentReference ->
                Log.d("debug", "Document successfully post with id ${documentReference.id}")
                startActivity(Intent(this, ThankYouActivity::class.java))
            }
            .addOnFailureListener{ e ->
                Log.d("debug", "An error has occur ${e.message}")

            }
    }
}