package com.example.apicall

import android.app.ProgressDialog
import android.net.DnsResolver
import android.net.DnsResolver.Callback
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.apicall.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        binding.fetchBtn.setOnClickListener {
            getData()
        }
    }

   private fun getData() {
        val progressDailog = ProgressDialog(this)
        progressDailog.setMessage("Please wait while data is fetching")
        progressDailog.show()
        RetrofitIntance.apiInterface.getData().enqueue(@RequiresApi(Build.VERSION_CODES.Q)
        object : Callback<ResponseDataClass?>,
            retrofit2.Callback<ResponseDataClass> {
            override fun onResponse(
                call: Call<ResponseDataClass>,
                response: Response<ResponseDataClass>
            ) {
                Glide.with(this@MainActivity).load(response.body()!!.message.toString())
                    .into(binding.imageView)
                progressDailog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataClass>, t: Throwable) {
                progressDailog.dismiss()
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAnswer(p0: ResponseDataClass, p1: Int) {
                TODO("Not yet implemented")
            }

            override fun onError(p0: DnsResolver.DnsException) {
                progressDailog.dismiss()
                Toast.makeText(this@MainActivity, "error occured", Toast.LENGTH_SHORT).show()
            }

        })
    }
}