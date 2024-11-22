package br.com.fiap.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.challenge.databinding.FragmentFormBinding
import com.google.firebase.firestore.FirebaseFirestore

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botão voltar
        binding.ivBack.setOnClickListener {
           findNavController().navigate(R.id.loginFragment)
        }

        // Botão enviar
        binding.btnEnviar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val email = binding.etEmail.text.toString()
            val telefone = binding.etTelefone.text.toString()
            val mensagem = binding.etMensagem.text.toString()

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || mensagem.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                enviarFormularioParaFirebase(nome, email, telefone, mensagem)
            }
        }
    }

    private fun enviarFormularioParaFirebase(nome: String, email: String, telefone: String, mensagem: String) {
        val formulario = hashMapOf(
            "nome" to nome,
            "email" to email,
            "telefone" to telefone,
            "mensagem" to mensagem
        )

        // Salvando os dados no Firestore
        db.collection("formularios")
            .add(formulario)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Formulário enviado com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Erro ao enviar formulário: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limparCampos() {
        binding.etNome.text.clear()
        binding.etEmail.text.clear()
        binding.etTelefone.text.clear()
        binding.etMensagem.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
