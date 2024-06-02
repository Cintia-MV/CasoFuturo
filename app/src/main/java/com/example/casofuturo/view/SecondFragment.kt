package com.example.casofuturo.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.casofuturo.R
import com.example.casofuturo.databinding.FragmentSecondBinding
import com.example.casofuturo.viewModel.CoursesViewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    private val viewModel : CoursesViewModel by activityViewModels()
    private var courseId : String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recibe id
        arguments?.let{bundle->

            courseId = bundle.getString("courseId")
            Log.d("Seleccion segFrament", courseId.toString())

        }

        courseId?.let { id ->
            viewModel.getCourseDetailByIdFromInternet(id)
        }

        viewModel.getCourseDetail().observe(viewLifecycleOwner, Observer {
            Log.d("Seleccion segFrament2", courseId.toString())

            var id = it.id
            var name = it.title

            //Cargar los datos desde la seleccion
            Glide.with(binding.ivLogo).load(it.image).into(binding.ivLogo)
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.previewDescription
            binding.tvMinimumSkill.text = "Conocimiento mínimo: ${it.minimumSkill}"
            binding.tvTuition.text = "Valor: ${it.tuition}"
            binding.tvDuration.text = "Duración: ${it.weeks} semanas"
            binding.tvModality.text = "Modalidad: ${it.modality}"
            binding.tvStart.text = "Inicio: ${it.start}"



            //Enviar correo electrónico
            binding.btMail.setOnClickListener {

                Log.d("Boton", "clic")

                //Inicializo el intent
                val intent = Intent(Intent.ACTION_SEND)

                //
                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"


                //Enviar correo a:
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("adminision@centrofuturo.cl"))

               //Asunto del correo
                intent.putExtra(Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este curso " +id

                    )

                //Mensaje del correo
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n"+
                            "Quisiera pedir información sobre este curso " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."

                )

                try {
                    startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }


        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}