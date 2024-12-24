package com.example.qwicktix

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.qwicktix.databinding.ActivityCreateEventBinding

const val TAG = "QwickTixTAG"
const val CAMERA_REQUEST_CODE = 0
class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGenerateQR.setOnClickListener {
            val name = binding.etName.text.toString()
            val date = binding.etDate.text.toString()
            val time = binding.etTime.text.toString()
            val location = binding.etLocation.text.toString()
            val description = binding.etDescription.text.toString()
            val ticketCapacity = binding.etTicketCapacity.text.toString()

            if(checkCameraPermission()){
                Toast.makeText(
                    this,
                    "Permission Exists",
                    Toast.LENGTH_SHORT
                ).show()
//                if(
//                    name.isNotBlank() &&
//                    date.isNotBlank() &&
//                    time.isNotBlank() &&
//                    location.isNotBlank() &&
//                    description.isNotBlank() &&
//                    ticketCapacity.isNotBlank()
//                ){
//                    generateQRCode()
//                }
//                else{
//                    Toast.makeText(this@CreateEventActivity,
//                        "Empty Fields not allowed!!",
//                        Toast.LENGTH_SHORT).show()
//                }
            }
            else{
                requestCameraPermission()
            }

        }
    }

//    fun generateQRCode(){
//    }

    private fun checkCameraPermission() : Boolean {
        return ActivityCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission(){
        val cameraPermission = listOf(Manifest.permission.CAMERA)
        ActivityCompat.requestPermissions(this,
            cameraPermission.toTypedArray(), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE && grantResults.isNotEmpty()){
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(
                        this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else{
                    Toast.makeText(
                        this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}