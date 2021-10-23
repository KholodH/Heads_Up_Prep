package com.example.headsup_prep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class add_new : AppCompatActivity() {
    private lateinit var btnadd:  Button
    private lateinit var btnback: Button
    private lateinit var addName: EditText
    private lateinit var taboo1:  EditText
    private lateinit var taboo2:  EditText
    private lateinit var taboo3:  EditText

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        btnadd = findViewById(R.id.bAdd)
        btnback=findViewById(R.id.bback)

        addName = findViewById(R.id.eName)
        taboo1=findViewById(R.id.taboo1)
        taboo2 = findViewById(R.id.taboo2)
        taboo3=findViewById(R.id.taboo3)


        btnadd.setOnClickListener {
            val f = celebrities.person(addName.text.toString(), taboo1.text.toString(),taboo2.text.toString(),taboo3.text.toString())

            addmember(f)}



    }
    private fun addmember(f: celebrities.person){

        if (apiInterface != null) {
            apiInterface.addmember(f)
                .enqueue(object : Callback<celebrities.person> {
                    override fun onResponse(
                        call: Call<celebrities.person>,
                        response:
                        Response<celebrities.person>
                    ) {
                        Toast.makeText(this@add_new, "user Added", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun onFailure(
                        call: Call<celebrities.person>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@add_new,
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                })


            btnback.setOnClickListener{
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
         }
    }
}