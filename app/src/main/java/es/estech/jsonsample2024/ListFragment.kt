package es.estech.jsonsample2024

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.estech.jsonsample2024.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lista = getAnimals()
        configRecycler(lista)
    }

    private fun configRecycler(list: ArrayList<Animal>) {
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerview.adapter = AnimalAdapter(list, object : AnimalAdapter.MyClick {
            override fun onHolderClick(animal: Animal) {
                val bundle = bundleOf("animal" to animal)
                findNavController().navigate(R.id.action_Animales_to_Animal, bundle)
            }
        })
    }

    fun getAnimals(): ArrayList<Animal> {
        val jsonString = readJsonFromAssets(requireContext(), "animales.json")
        val typeToken = TypeToken.getParameterized(ArrayList::class.java, Animal::class.java).type
        val animalList = Gson().fromJson<ArrayList<Animal>>(jsonString, typeToken)
        return animalList
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}