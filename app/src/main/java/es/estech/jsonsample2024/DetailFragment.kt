package es.estech.jsonsample2024

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import es.estech.jsonsample2024.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animal = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("animal", Animal::class.java)
        } else {
            arguments?.getParcelable("animal")
        }
        if (animal != null) {
            fillAnimalData(animal)
        }
    }

    private fun fillAnimalData(animal: Animal) {
        binding.tvName.text = animal.name
        binding.tvActivity.text = animal.activeTime
        binding.tvDiet.text = animal.diet
        binding.tvType.text = animal.animalType
        binding.tvHabitat.text = animal.habitat
        binding.tvLocation.text = animal.geoRange

        Glide.with(this).load(animal.imageLink).into(binding.ivImage)

        (requireActivity() as MainActivity).supportActionBar?.title = animal.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}