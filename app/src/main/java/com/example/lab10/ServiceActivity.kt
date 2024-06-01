package com.example.lab10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab10.databinding.ActivityServiceBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Firebase.firestore
        readFireStoreData()

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_service)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    private fun readFireStoreData(){
        db.collection("customer")
            .get()
            .addOnCompleteListener{
                val result:StringBuffer = StringBuffer()
                if(it.isSuccessful){
                    for (document in it.result!!){
                        val city = document.get("city") ?: "N/A"
                        val country = document.get("country") ?: "N/A"
                        val name = document.get("name") ?: "N/A"
                        val phone = document.get("phone") ?: "N/A"

                        result.append("Name ").append(name).append("\n")
                            .append("Phone ").append(phone).append("\n")
                            .append("City ").append(city).append("\n")
                            .append("Country ").append(country).append("\n").append("\n").append("\n")

                        binding.resultID.text = result.toString().trim()
                    }
                }
                else{
                    println(it.result.toString())
                }
            }
    }
}