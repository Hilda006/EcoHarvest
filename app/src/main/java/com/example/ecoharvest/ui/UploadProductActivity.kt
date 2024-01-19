package com.example.ecoharvest.ui

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.ecoharvest.R
import com.example.ecoharvest.databinding.ActivityUploadProductBinding
import com.example.ecoharvest.databinding.AlertDialogBinding
import com.example.ecoharvest.utils.createTimeFile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class UploadProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUploadProductBinding
    private lateinit var photoUrl: String
    private var getFile: File? = null
    private lateinit var storageReference: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var selectedImg: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()

        supportActionBar?.let {
            it.title = getString(R.string.add_sales)
            it.setDisplayHomeAsUpEnabled(true)
        }

        binding.upload.setOnClickListener {
            showDialog()
        }
        binding.btnUpload.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        binding.progressBar.visibility = View.VISIBLE
        storageReference = storageReference.child(System.currentTimeMillis().toString())
        selectedImg?.let {
            storageReference.putFile(it).addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        val addData = HashMap<String, Any>()
                        addData["image"] = uri.toString()
                        addData["name"] = binding.nameProduct.text.toString()
                        addData["description"] = binding.desc.text.toString()
                        addData["amount"] = binding.amount.text.toString()

                        firebaseFirestore.collection("data").add(addData)
                            .addOnCompleteListener { taskUpload ->
                                if (taskUpload.isSuccessful) {
                                    Toast.makeText(this, "Upload Success", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, MartActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
                                }
                                binding.progressBar.visibility = View.GONE
                                binding.imageView21.setImageResource(R.drawable.add_a_photo)

                            }
                    }
                } else {
                    Toast.makeText(this, "Upload Failed. Try Again!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //    Gallery
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launchGallery.launch(chooser)
    }

    private var launchGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            selectedImg = result.data?.data
            binding.imageView21.setImageURI(selectedImg)
        }
    }
    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createTimeFile(application).also {
            val photoUri: Uri = FileProvider.getUriForFile(this, "com.example.ecoharvest", it)
            photoUrl = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            launchCamera.launch(intent)
        }
    }

    private var launchCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val file = File(photoUrl)
            getFile = file
            val result = BitmapFactory.decodeFile(file.path)
            binding.imageView21.setImageBitmap(result)
        }

    }

    private fun showDialog() {
        val binding = AlertDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnCamera.setOnClickListener {
            if (allPermissionsGranted()) {
                takePhoto()
                dialog.dismiss()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    PERMISSION_REQUIRED,
                    REQUEST_CODE_PERMISSIONS
                )
            }


        }
        binding.btnGallery.setOnClickListener {
            startGallery()
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun allPermissionsGranted() = PERMISSION_REQUIRED.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val PERMISSION_REQUIRED = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val LOCATION_REQUEST_CODE = 100

    }
}