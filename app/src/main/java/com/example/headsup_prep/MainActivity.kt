package com.example.headsup_prep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var names:ArrayList<String>
    private lateinit var btnaddnew:Button
    private lateinit var btnupdat:Button
    private lateinit var addName:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        names = arrayListOf()
        btnaddnew = findViewById(R.id.baddnew)
        btnupdat = findViewById(R.id.btnup)
        addName=findViewById(R.id.etup)
        val tvMain = findViewById(R.id.tvMain) as TextView


        apiInterface?.getmem()?.enqueue(object : Callback<ArrayList<celebrities.person>> {
            override fun onResponse(
                call: Call<ArrayList<celebrities.person>>,
                response: Response<ArrayList<celebrities.person>>
            ) {
                var usersData: String = ""
                for (User in response.body()!!) {
                    usersData = usersData + User.name + "\n" + User.taboo1 + "\n" + User.taboo2 + "\n" +  User.taboo3 + "\n" +"\n"
                }
                tvMain.text = usersData
            }


            override fun onFailure(call: Call<ArrayList<celebrities.person>>, t: Throwable) {
                Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
        btnaddnew.setOnClickListener{
            intent = Intent(this, add_new::class.java)
            startActivity(intent)
        }
//        button.setOnClickListener {
//            val intent = Intent(this, Activity2::class.java)
//            startActivity(intent)
//        }
    }
}