package com.alanhu.tools.softinputvisibilitylistener

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alanhu.tools.softinputvisibilitylistener.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var unregistrar: SoftInputVisibilityUnregistrar? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.apply {
            setOnClickListener {
                unregistrar?.unregister()
                unregistrar = registerSoftInputVisibilityListener { isVisible ->
                    binding.tvSoftInputState.text = "SoftInput isVisible: $isVisible"
                    if (!isVisible) {
                        binding.editText.clearFocus()
                    }
                }
                binding.tvRegisterState.text =
                    "SoftInput Unregistrar: Registered (${unregistrar.hashCode()})"
            }
            callOnClick()
        }

        binding.btnUnregister.setOnClickListener {
            unregistrar?.unregister()
            binding.tvRegisterState.text = "SoftInput Unregistrar: Unregistered"
        }
    }
}