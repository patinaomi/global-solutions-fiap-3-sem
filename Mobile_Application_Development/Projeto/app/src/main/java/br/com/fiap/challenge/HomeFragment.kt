package br.com.fiap.challenge

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.fiap.challenge.service.RetrofitInstance
import br.com.fiap.challenge.R
import br.com.fiap.challenge.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        // Obter o clienteId das SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val clienteId = sharedPref.getString("clienteId", "") ?: ""

        if (clienteId.isNotEmpty()) {
            lifecycleScope.launch {
                try {
                    // Chamada à API para buscar o nome do usuário
                    val response = RetrofitInstance.api.getUser(clienteId)
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        val nomeUsuario = userResponse?.nome ?: getString(R.string.user)

                        // Exibir o nome do usuário
                        binding.textViewWelcome.text = getString(R.string.welcome_message, nomeUsuario)

                        // Salvar o nome do usuário nas SharedPreferences para uso futuro
                        with(sharedPref.edit()) {
                            putString("nomeUsuario", nomeUsuario)
                            apply()
                        }
                    } else {
                        // Exibe mensagem genérica caso a chamada falhe
                        binding.textViewWelcome.text = getString(R.string.welcome_message, getString(R.string.user))
                    }
                } catch (e: Exception) {
                    // Exibe mensagem genérica em caso de erro
                    binding.textViewWelcome.text = getString(R.string.welcome_message, getString(R.string.user))
                }
            }
        } else {
            // Caso não haja clienteId, exibe mensagem genérica
            binding.textViewWelcome.text = getString(R.string.welcome_message, getString(R.string.user))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
