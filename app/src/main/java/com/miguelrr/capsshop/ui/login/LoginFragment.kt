package com.miguelrr.capsshop.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.miguelrr.capsshop.CapsShopApp.Companion.idUserSP
import com.miguelrr.capsshop.databinding.FragmentLoginBinding
import com.miguelrr.capsshop.domain.model.LoginUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val loginViewModel : LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener{
            if(binding.tiLoginEmail.text.toString().isNotEmpty() && binding.tiLoginPassword.text.toString().isNotEmpty()){
                val emailUsername = binding.tiLoginEmail.text.toString()
                val password = binding.tiLoginPassword.text.toString()
                val loginUser = LoginUser(emailUsername, password)
                loginViewModel.logIn(loginUser)
            }
        }

        loginViewModel.user.observe(viewLifecycleOwner){
            if(it.idUser != -1){
                Toast.makeText(context, "Bienvenido ${it.username}", Toast.LENGTH_SHORT).show()
                idUserSP.saveId(it.idUser)
                navigateHome()
            }else{
                Toast.makeText(context, it.reason, Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLoginSignup.setOnClickListener{
            navigateSignUp()
        }
    }

    private fun navigateSignUp() {
        findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSignup())
    }

    private fun navigateHome() {
        findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationHome())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}