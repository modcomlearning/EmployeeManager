package com.example.employeeregister

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class DeleteEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end insets

        //Find  1 Edittext and 1 Button
        val id_number = findViewById<EditText>(R.id.id_number)
        val btndelete = findViewById<Button>(R.id.btndelete)
        btndelete.setOnClickListener {
            //Create a JSON Object that will Hold input value from Edit Texts.
            val body = JSONObject() //add id_number to json object
            body.put("id_number", id_number.text.toString())

            //Base API URL/End Point
            val api = "https://modcom.pythonanywhere.com/employees"

            //Access Helper class
            val helper = ApiHelper(applicationContext)

            //Trigger the delete function in helper class
            // We provide the api link and data body, id number to delete
            helper.delete(api, body)
            //DONE
        }//End Listener
    }//end oncreate
}//end class