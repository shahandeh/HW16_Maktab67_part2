package com.example.github.ui.uploadimage

import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.databinding.FragmentUploadImageBinding
import com.example.github.di.App
import com.example.github.ui.ViewModelFactory
import com.example.github.util.toByteArrayConverter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UploadImageFragment : Fragment(R.layout.fragment_upload_image) {

    private val uploadImageViewModel by viewModels<UploadImageViewModel> { ViewModelFactory((requireActivity().application as App).serviceLocator.repository) }

    private val args: UploadImageFragmentArgs by navArgs()

    private lateinit var byteArray: ByteArray
    private lateinit var imageFromCamera: ActivityResultLauncher<Void>
    private lateinit var imageFromGallery: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUploadImageBinding.bind(view)

        imageFromCamera =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                if (it != null){
                    byteArray = it.toByteArrayConverter()
                    binding.imageViewFragmentUploadImage.setImageBitmap(it)
                }
            }
        imageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null){
                byteArray =
                    requireActivity().contentResolver?.openInputStream(it)?.readBytes() as ByteArray
                binding.imageViewFragmentUploadImage.setImageURI(it)
            }
        }

        binding.takeImageFragmentUploadImage.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Take Image")
                .setMessage("Select Image Source")
                .setNegativeButton("CAMERA") { dialog, which ->
                    takeImageFromCamera()
                }
                .setPositiveButton("GALLERY") { dialog, which ->
                    takeImageFromGallery()
                }
                .show()
        }

        binding.uploadImageFragmentUploadImage.setOnClickListener {
            uploadImage()
        }

    }

    private fun takeImageFromCamera() {
        imageFromCamera.launch(null)
    }

    private fun takeImageFromGallery() {
        imageFromGallery.launch("image/*")
    }

    private fun uploadImage() {
        lifecycleScope.launchWhenStarted {
            uploadImageViewModel.uploadImage(args.id, byteArray)
        }
    }

}

