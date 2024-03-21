package com.example.employeeregister

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray

class GetEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//End Insets

        //Find Progress Bar, Upon opening this Activity this ProgressBar starts Loading Automatically
        val progress = findViewById<ProgressBar>(R.id.progress)
        //Base API URL/Endpoint
        val api = "https://modcom.pythonanywhere.com/employees"

        //Access the Helper
        val helper = ApiHelper(applicationContext)
        helper.get(api, object : ApiHelper.CallBack{
            override fun onSuccess(result: String?) {
                //Convert the GET results to JSON Array
                val employeeJSONArray = JSONArray(result.toString())
                //Loop Each Object in the JSONArray
                (0 until employeeJSONArray.length()).forEach {
                    val employee = employeeJSONArray.getJSONObject(it)
                    //For each Object Find the TextView and Append the 5 C0lumns
                    val empdata = findViewById<TextView>(R.id.empdata)
                    empdata.append("ID:"+employee.get("id_number")+"\n")
                    empdata.append("Username:"+employee.get("username")+"\n")
                    empdata.append("Others: "+employee.get("others")+"\n")
                    empdata.append("Salary"+employee.get("salary")+"\n")
                    empdata.append("Dept: "+employee.get("department")+"\n")
                    empdata.append("\n\n")
                    //Since its a Loop, All Employees are Loaded in the TextView.
                }
                //Stop Progress from Loading
                progress.visibility = View.GONE
            }
        })
    }//end Oncreate
}//end class