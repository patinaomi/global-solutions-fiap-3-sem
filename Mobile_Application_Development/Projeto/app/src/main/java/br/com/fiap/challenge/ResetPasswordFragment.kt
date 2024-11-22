package br.com.fiap.challenge

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.fiap.challenge.models.UpdatePasswordRequest
import br.com.fiap.challenge.service.RetrofitInstance
import br.com.fiap.challenge.R
import br.com.fiap.challenge.databinding.FragmentResetPasswordBinding
import kotlinx.coroutines.launch

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btEnviar.setOnClickListener {
            val novaSenha = binding.etNovaSenha.text.toString().trim()
            val confirmeSenha = binding.etConfirmeSenha.text.toString().trim()

            if (novaSenha.isEmpty() || confirmeSenha.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isPasswordValid(novaSenha)) {
                Toast.makeText(requireContext(), "A senha não atende aos requisitos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (novaSenha != confirmeSenha) {
                Toast.makeText(requireContext(), "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
            val clienteId = sharedPref.getString("clienteId", "") ?: ""

            lifecycleScope.launch {
                try {
                    val request = UpdatePasswordRequest(clienteId, novaSenha)
                    val response = RetrofitInstance.api.updatePassword(request)

                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.loginFragment) //volta para o login
                    } else {
                        Toast.makeText(requireContext(), "Erro ao atualizar a senha", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Valida se a senha atende aos requisitos de segurança.
     */
    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*])[A-Za-z\\d!@#\$%^&*]{8,}$"
        return password.matches(passwordPattern.toRegex())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
