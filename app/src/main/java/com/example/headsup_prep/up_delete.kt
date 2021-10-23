package com.example.headsup_prep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class up_delete : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etTaboo1: EditText
    private lateinit var etTaboo2: EditText
    private lateinit var etTaboo3: EditText
    private lateinit var btDelete: Button
    private lateinit var btUpdate: Button
    private lateinit var btBack: Button
    private var celebrityID = 0

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_delete)

        btUpdate = findViewById(R.id.bupdate)
        btDelete = findViewById(R.id.bdelete)
        btBack = findViewById(R.id.back)
        etName = findViewById(R.id.uName)
        etTaboo1 = findViewById(R.id.utaboo1)
        etTaboo2 = findViewById(R.id.utaboo2)
        etTaboo3 = findViewById(R.id.utaboo3)
        celebrityID = intent.extras!!.getInt("celebrityID", 0)

        btDelete.setOnClickListener {
            // Make sure to add a confirmation alert here
            deleteCelebrity()
        }

        btUpdate.setOnClickListener {
            if (etName.text.isNotEmpty() && etTaboo1.text.isNotEmpty() &&
                etTaboo2.text.isNotEmpty() && etTaboo3.text.isNotEmpty()
            ) {
                updateCelebrity()
            } else {
                Toast.makeText(this, "One or more fields is empty", Toast.LENGTH_LONG).show()
            }
        }


    }
        private fun getCelebrity() {
            if (apiInterface != null) {
                apiInterface.getCelebrity(celebrityID).enqueue(object : Callback<celebrities.person> {
                    override fun onResponse(call: Call<celebrities.person>, response: Response<celebrities.person>) {

                        val celebrity = response.body()!!
                        etName.setText(celebrity.name)
                        etTaboo1.setText(celebrity.taboo1)
                        etTaboo2.setText(celebrity.taboo2)
                        etTaboo3.setText(celebrity.taboo3)
                    }

                    override fun onFailure(call: Call<celebrities.person>, t: Throwable) {

                        Toast.makeText(
                            this@up_delete,
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
            }
        }



    private fun updateCelebrity(){

        if (apiInterface != null) {
            apiInterface.updateCelebrity(
                celebrityID,
                celebrities.person(
                    etName.text.toString(),
                    etTaboo1.text.toString(),
                    etTaboo2.text.toString(),
                    etTaboo3.text.toString(),
                    celebrityID
                )
            ).enqueue(object: Callback<celebrities.person> {
                override fun onResponse(call: Call<celebrities.person>, response: Response<celebrities.person>) {
                    Toast.makeText(this@up_delete, "Celebrity Updated", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<celebrities.person>, t: Throwable) {
                    Toast.makeText(this@up_delete, "Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

        private fun deleteCelebrity() {

            if (apiInterface != null) {
                apiInterface.deleteCelebrity(celebrityID).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            this@up_delete,
                            "Celebrity Deleted",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(
                            this@up_delete,
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
            }


        }
    }
