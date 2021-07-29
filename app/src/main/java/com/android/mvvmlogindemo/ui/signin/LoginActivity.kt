package com.android.mvvmlogindemo.ui.signin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.mvvmlogindemo.R
import com.android.mvvmlogindemo.databinding.ActivityLoginBinding
import com.android.mvvmlogindemo.utils.makeToast
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    // Inject ViewModel
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.loginResponse.observe(this, Observer {
            Log.d("test", "success : ${it.fullname}")
            this.makeToast(it.fullname)
        })

        loginViewModel.isError.observe(this, Observer {
            Log.d("test", "error :$it ")
            this.makeToast(it)
        })
    }
}