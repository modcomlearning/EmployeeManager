package com.example.employeeregister

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class PostEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_post_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end Insets

        //Find All 5 Edittexts and 1 Button
        val id_number = findViewById<EditText>(R.id.id_number)
        val username = findViewById<EditText>(R.id.username)
        val others = findViewById<EditText>(R.id.others)
        val salary = findViewById<EditText>(R.id.salary)
        val department = findViewById<EditText>(R.id.department)
        val btnsave = findViewById<Button>(R.id.btnsave)
        //Listen for btn save is being clicked by user
        btnsave.setOnClickListener {
            //Create a JSON Object that will Hold input values from Edit Texts.
            val body = JSONObject()
            //add id_number,username,others,salary, department to json object as body
            body.put("id_number", id_number.text.toString())
            body.put("username", username.text.toString())
            body.put("others", others.text.toString())
            body.put("salary", salary.text.toString())
            body.put("department", department.text.toString())
            //Base API URL/End Point
            val api = "https://modcom.pythonanywhere.com/employees"
            //Access helper
            //Trigger the post function in helper class
            // We provide the api link and data body.
            val helper = ApiHelper(applicationContext)
            //Post the body with your data/employees details to our api endpoint
            helper.post(api, body)
        }//end Onclick listener
    }//end oncreate
}//end class