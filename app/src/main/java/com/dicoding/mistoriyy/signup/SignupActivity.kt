package com.dicoding.mistoriyy.signup



import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.*
import androidx.core.view.*
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mistoriyy.R
import com.dicoding.mistoriyy.databinding.ActivitySignupBinding
import com.dicoding.mistoriyy.login.LoginActivity


class SignupActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signupViewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signupViewModelFactory: SignupViewModelFactory = SignupViewModelFactory.getInstance(application)
        signupViewModel = ViewModelProvider(this, signupViewModelFactory)[SignupViewModel::class.java]

      signupViewModel.successDialog.observe(this) {
            successDialog(it)
        }

       signupViewModel.errorDialog.observe(this) {
            errorDialog(it)
        }

        signupViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressIndicator.visibility = View.VISIBLE
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            } else {
                binding.progressIndicator.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        signupAction()
    }

    private fun errorDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(this)
            .setMessage(errorMessage)
            .setPositiveButton(R.string.confirmation) { dialog, _ ->
                dialog.dismiss()
            }
        val alertDialog = builder.create()
        alertDialog.show()
        val messageView = alertDialog.findViewById<TextView>(android.R.id.message)
        @Suppress("DEPRECATION")
        messageView?.setTextColor(resources.getColor(R.color.black))
        alertDialog.window?.setBackgroundDrawableResource(R.color.white)
    }

    private fun successDialog(message: String) {
        val builder = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.confirmation) { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        val messageView = alertDialog.findViewById<TextView>(android.R.id.message)
        messageView?.setTextColor(resources.getColor(R.color.black))
        alertDialog.window?.setBackgroundDrawableResource(R.color.white)
    }

    private fun signupAction() {
        binding.buttonRegister.setOnClickListener{
            val userName = binding.edSignupName.text.toString().trim()
            val userEmail = binding.edSignupEmail.text.toString().trim()
            val userPassword = binding.edSignupPassword.text.toString().trim()
            signupViewModel.signupUser(userName, userEmail, userPassword)
        }
    }
}