package com.lazymindapps.naxamobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lazymindapps.naxamobile.databinding.ActivityMainBinding
import com.lazymindapps.naxamobile.model.models.UserLogin
import com.lazymindapps.naxamobile.sharedPref.SharedPrefClass
import com.lazymindapps.naxamobile.utils.Constants.Companion.TOKEN
import com.lazymindapps.naxamobile.view.ProfileActivity
import com.lazymindapps.naxamobile.viewmodel.NaxaViewModel
import com.lazymindapps.naxamobile.viewmodel.NaxaViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
   // private val sharedPrefFile = "naxasharedpreference"
    lateinit var sharedPrerClass:SharedPrefClass



   lateinit var sharedPreferences:SharedPreferences 
    val viewModel: NaxaViewModel by viewModels {
        NaxaViewModelFactory((application as NaxaApplication).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrerClass = SharedPrefClass(this)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
     //  sharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)


        checkTokenLoginAccessFromSharedPreference()

        dataBaseOperation()

        binding.btnLogin.setOnClickListener {
            if (binding.etEmail!=null && binding.etPassword!=null){
                var userlogin = UserLogin(binding.etEmail.text.toString(),binding.etPassword.text.toString())
                loginOperation(userlogin)


            }
            else{
                Toast.makeText(this,"Please fill email and password properly",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkTokenLoginAccessFromSharedPreference(){
        val sharedToken = sharedPrerClass.getToken()
        if (sharedToken.equals("0")){
            Toast.makeText(applicationContext,"Please login first",Toast.LENGTH_LONG).show()
        }else{
            
            startActivity(Intent(applicationContext,ProfileActivity::class.java))
            finish()


        }
    }

    fun dataBaseOperation() {

        viewModel.users.observe(this, Observer {
            Log.d("User from internet", it.data.toString())
            val usersData = it.data
            for (user in usersData) {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        viewModel.insertUserInDb(user)
                    }
                }
            }

        })

    }

    fun loginOperation(userLogin: UserLogin){
        runBlocking {
            withContext(Dispatchers.IO){
                val result =  viewModel.login(userLogin)
                if (result=="null"){
                    runOnUiThread(Runnable(){

                        Toast.makeText(
                            applicationContext,
                            "Enter valid email and password",
                            Toast.LENGTH_LONG
                        ).show()
                    })


                }
                else{
                    Log.d("Token result", result)
                    Log.d("Token result", result)
                  sharedPrerClass.saveToken(result)
                    startActivity(Intent(applicationContext,ProfileActivity::class.java))
                    finish()


                }


            }
        }


    }




}