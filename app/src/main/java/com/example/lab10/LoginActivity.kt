package com.example.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab10.databinding.ActivityLoginBinding
import com.example.lab10.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.loginBtn.setOnClickListener {

            if(binding.emailLoginId.text.trim().toString().isNotEmpty() ||
                binding.passwordLoginId.text.trim().toString().isNotEmpty()){
                loginUser(binding.emailLoginId.text.toString(), binding.passwordLoginId.text.toString())
            }
            else{
                Snackbar.make(
                    binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_login)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            task ->
            if(task.isSuccessful){
                val intent = Intent(this, ServiceActivity::class.java)
                startActivity(intent)
            }
            else{
                Snackbar.make(
                    binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
            .addOnFailureListener{
                err ->
                Snackbar.make(
                    binding.root,
                    err.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }
}