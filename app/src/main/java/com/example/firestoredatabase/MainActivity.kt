package com.example.firestoredatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var tvOutput: TextView
    private lateinit var edtAge: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnRead: Button

    private lateinit var firebaeDatabae: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOutput = findViewById(R.id.tvOutput)
        edtAge = findViewById(R.id.edtAge)
        edtName = findViewById(R.id.edtName)
        tvOutput = findViewById(R.id.tvOutput)
        btnSave = findViewById(R.id.btnSave)
        btnRead = findViewById(R.id.btnRead)

        firebaeDatabae  = FirebaseFirestore.getInstance()

        btnSave.setOnClickListener() {
            saveData()
        }

        btnRead.setOnClickListener() {
            readData()
        }

    }

    private fun saveData() {
        val name = edtName.text.toString().trim()
        val age = edtAge.text.toString().trim().toInt()

        val date = hashMapOf<String,Any>()
        date.put("name",name)
        date.put("age",age)

//        firebaeDatabae.collection("users").document("pserson1")  // Without document name  then it will be replaced with existing one.
//            .set(date)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    tvOutput.text = "Data in saved"
//                } else {
//                    tvOutput.text = it.exception?.message
//                }
//            }

        val docData :HashMap<String,Any?> = hashMapOf(
            "string_example" to "My name is Sakib",
            "boolean_example" to true,
            "number_example" to 123456,
            "date_example" to Timestamp(Date()), //make sure to com.google.firebase.Timestamp
            "list_example" to arrayListOf(1,2,3,4,5),
            "null_example" to null,
            )

        val nestedData : HashMap<String,Any> =  hashMapOf()
        nestedData.put("a",5)
        nestedData.put("b",7)

        docData["object_example"] = nestedData

        firebaeDatabae.collection("test").document("data") // Without document name then it will NOT be replaced.
            .set(docData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    tvOutput.text = "Data in saved"
                } else {
                    tvOutput.text = it.exception?.message
                }
            }

//        firebaeDatabae.document("users/person1") // We can also create collection and document like this.
//        firebaeDatabae.collection("users").document() // Without document name then it will NOT be replaced.
//            .set(date)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    tvOutput.text = "Data in saved"
//                } else {
//                    tvOutput.text = it.exception?.message
//                }
//            }

    }

    private fun readData() {

    }

}