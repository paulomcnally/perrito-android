package com.uca.apps.perrito

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.uca.apps.perrito.api.Api
import com.uca.apps.perrito.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var name: TextInputEditText? = null
    private var email: TextInputEditText? = null
    private var code: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        save()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun save() {
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        code = findViewById(R.id.code)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            create()
        }
    }

    private fun validate(): Boolean {
        when {
            name?.text.toString().isEmpty() -> {
                Toast.makeText(applicationContext, R.string.activity_main_name_required, Toast.LENGTH_LONG).show()
                return false
            }
            email?.text.toString().isEmpty() -> {
                Toast.makeText(applicationContext, R.string.activity_main_email_required, Toast.LENGTH_LONG).show()
                return false
            }
            code?.text.toString().isEmpty() -> {
                Toast.makeText(applicationContext, R.string.activity_main_code_required, Toast.LENGTH_LONG).show()
                return false
            }
            else -> return true
        }
    }

    private fun create() {
        if (validate()) {
            val userModel = UserModel()
            userModel.code = code?.text.toString()
            userModel.email = email?.text.toString()
            userModel.name = name?.text.toString()

            val call = Api.instance().userCreate(userModel)

            call.enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    Toast.makeText(applicationContext, response.body()?.name, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UserModel>, throwable: Throwable) {
                    Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_LONG).show()
                }
            })


        }
    }
}
