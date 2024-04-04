package com.example.coindesk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.coindesk.DataClass.Coin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var usdCurrency: TextView
    lateinit var usdRate: TextView
    lateinit var usdUpdate: TextView
    lateinit var eurCurrency: TextView
    lateinit var eurRate: TextView
    lateinit var eurUpdate: TextView
    lateinit var gpbCurrency: TextView
    lateinit var gpbRate: TextView
    lateinit var gpbUpdate: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usdCurrency = findViewById(R.id.textViewUSD)
        usdRate = findViewById(R.id.textViewUSDCurrency)
        usdUpdate = findViewById(R.id.textViewUSDUpdated)
        eurCurrency = findViewById(R.id.textViewEUR)
        eurRate = findViewById(R.id.textViewEURCurrency)
        eurUpdate = findViewById(R.id.textViewEURUpdated)
        gpbCurrency = findViewById(R.id.textViewGBP)
        gpbRate = findViewById(R.id.textViewGBPCurrency)
        gpbUpdate = findViewById(R.id.textViewGBPUpdated)
        getCoinInfo()
    }

    private fun getCoinInfo() {

        val retrofitService = CoinRetrofitClient.getService()
        val service = retrofitService.getCoinData()

        service.enqueue(object : Callback<Coin?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Coin?>, response: Response<Coin?>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        coin ->
                        usdCurrency.text = coin.bpi.USD.code
                        usdRate.text = "$${coin.bpi.USD.rate}"
                        usdUpdate.text = coin.time.updated

                        eurCurrency.text = coin.bpi.EUR.code
                        eurRate.text = "$${coin.bpi.EUR.rate}"
                        eurUpdate.text = coin.time.updated

                        gpbCurrency.text = coin.bpi.GBP.code
                        gpbRate.text = "$${coin.bpi.GBP.rate}"
                        gpbUpdate.text = coin.time.updated
                    }
                }
            }

            override fun onFailure(call: Call<Coin?>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Please connect to internet",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
