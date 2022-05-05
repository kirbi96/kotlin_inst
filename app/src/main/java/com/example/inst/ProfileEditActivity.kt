package com.example.inst
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.inst.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var nameInput: EditText
    private lateinit var usernameInput: EditText
    private lateinit var websiteInput: EditText
    private lateinit var bioInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        init()
    }

    private fun init() {
        val closeBtn = findViewById<ImageView>(R.id.profile_edit_close)
        closeBtn.setOnClickListener {
            finish()
        }

        nameInput = findViewById(R.id.name_input)
        usernameInput = findViewById(R.id.username_input)
        websiteInput = findViewById(R.id.site_input)
        bioInput = findViewById(R.id.bio_input)
        emailInput = findViewById(R.id.email_input)
        phoneInput = findViewById(R.id.phone_input)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(user!!.uid).addListenerForSingleValueEvent(object:
            ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val user = data.getValue(User::class.java)

                nameInput.setText(user!!.name, TextView.BufferType.EDITABLE)
                usernameInput.setText(user.username, TextView.BufferType.EDITABLE)
                websiteInput.setText(user.website, TextView.BufferType.EDITABLE)
                bioInput.setText(user.bio, TextView.BufferType.EDITABLE)
                emailInput.setText(user.email, TextView.BufferType.EDITABLE)
                phoneInput.setText(user.phone, TextView.BufferType.EDITABLE)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR", error.toException().toString())
            }
        })
    }
}