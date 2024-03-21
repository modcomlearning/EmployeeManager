package com.example.employeeregister

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class UpdateEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end Insets

        //Find All 2 Edittexts and 1 Button
        val id_number = findViewById<EditText>(R.id.id_number)
        val salary = findViewById<EditText>(R.id.salary)
        val btnupdate = findViewById<Button>(R.id.btnupdate)
        btnupdate.setOnClickListener {
            //Create a JSON Object that will Hold input values from Edit Texts.
            //We are updating salary based on id_number provided
            val body = JSONObject() //add id_number and salary to json object
            body.put("id_number", id_number.text.toString())
            body.put("salary", salary.text.toString())

            //Base API URL/Endpoint
            val api = "https://modcom.pythonanywhere.com/employees"

            //Access the Helper
            val helper = ApiHelper(applicationContext)

              //Trigger the put function in helper class
              // We provide the api link and data body
            helper.put(api, body)
            //DONE
        }//End OnCLick
    }//end Oncreate
}//end Class