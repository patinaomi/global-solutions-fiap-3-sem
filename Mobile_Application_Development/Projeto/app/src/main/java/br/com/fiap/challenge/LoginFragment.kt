package br.com.fiap.challenge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.fiap.challenge.models.LoginRequest
import br.com.fiap.challenge.service.RetrofitInstance
import br.com.fiap.challenge.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

        binding.tvEsqueciSenha.setOnClickListener {
            findNavController().navigate(R.id.forgotPasswordFragment)
        }

        binding.btAcessar.setOnClickListener {
            val email = binding.etLogin.text.toString().trim()
            val senha = binding.etSenha.text.toString().trim()

            // Validações básicas
            if (email.isBlank() || senha.isBlank()) {
                binding.tvErroLogin.text = getString(R.string.e_mail_e_senha_obrigatorios)
                binding.tvErroLogin.visibility = View.VISIBLE
                return@setOnClickListener
            }

            // Tentar login com fallback
            realizarLoginComFallback(email, senha)
        }

        binding.btFormulario.setOnClickListener {
            findNavController().navigate(R.id.formFragment)
        }
    }

    private fun realizarLoginComFallback(email: String, senha: String) {
        lifecycleScope.launch {
            try {
                // Tenta login via API
                val response = RetrofitInstance.api.login(LoginRequest(email, senha))
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.message == "Login bem-sucedido") {
                        // Salvar ID do cliente e nome do usuário
                        salvarInformacoesUsuario(loginResponse.id, loginResponse.name)
                        findNavController().navigate(R.id.homeFragment)
                    } else {
                        // Tenta login pelo Firebase
                        realizarLoginFirebase(email, senha)
                    }
                } else {
                    // Tenta login pelo Firebase em caso de erro no servidor
                    realizarLoginFirebase(email, senha)
                }
            } catch (e: Exception) {
                // Tenta login pelo Firebase em caso de erro na requisição
                realizarLoginFirebase(email, senha)
            }
        }
    }

    private fun realizarLoginFirebase(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login pelo Firebase bem-sucedido
                    val user = auth.currentUser
                    val nomeUsuario = user?.displayName ?: "Usuário"
                    val clienteId = user?.uid ?: ""
                    salvarInformacoesUsuario(clienteId, nomeUsuario)
                    findNavController().navigate(R.id.homeFragment)
                } else {
                    // Exibe mensagem de erro final
                    binding.tvErroLogin.text = getString(R.string.credenciais_invalidas)
                    binding.tvErroLogin.visibility = View.VISIBLE
                }
            }
            .addOnFailureListener {
                // Erro ao tentar login pelo Firebase
                binding.tvErroLogin.text = getString(R.string.falha_no_login)
                binding.tvErroLogin.visibility = View.VISIBLE
            }
    }

    private fun salvarInformacoesUsuario(clienteId: String, nome: String) {
        val sharedPref = requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("clienteId", clienteId)
            putString("nomeUsuario", nome)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
