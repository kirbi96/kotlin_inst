package com.example.inst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class LoginActivity : AppCompatActivity(), KeyboardVisibilityEventListener, TextWatcher,
    View.OnClickListener {
    private lateinit var btnLogin: Button
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var scrollview: ScrollView
    private lateinit var createAccount: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.init()
        KeyboardVisibilityEvent.setEventListener(this, this)

        btnLogin.isEnabled = false
        emailInput.addTextChangedListener(this)
        passwordInput.addTextChangedListener(this)
        btnLogin.setOnClickListener(this)
    }

    private fun init() {
        btnLogin = findViewById(R.id.button_login)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(view: View) {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {

            }
        }
    }

    override fun onVisibilityChanged(isKeyboardOpen: Boolean) {
        scrollview = findViewById(R.id.scrollview)
        createAccount  = findViewById(R.id.create_account_text)

        if (isKeyboardOpen){
            scrollview.scrollTo(0, scrollview.bottom)
            createAccount.visibility = View.GONE
        } else {
            scrollview.scrollTo(0, scrollview.top)
            createAccount.visibility = View.VISIBLE
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        btnLogin.isEnabled = emailInput.text.toString().isNotEmpty() && passwordInput.text.toString().isNotEmpty()
    }
}