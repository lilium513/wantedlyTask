package com.liliumproject.android.wantedlytask

import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONStringer
import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.logging.Logger

/**
 * Created by kt on 2017/07/31.
 */
public open class ApiProceeding: AsyncTask<String,Void,String>(){
    internal var client = OkHttpClient()

    override fun doInBackground(vararg p0: String?): String {
        val q: String? = p0[0]
        var url: String = "https://api.github.com/search/repositories?q="+p0[0]
        var res: String = ""
        var name: String =""
        try {
            res = run(url)
            val resJson = JSONObject(res)
            val reps = resJson.getJSONArray("items")
             name =reps.getJSONObject(0).getString("name")

                    } catch(e: IOException) {
            e.printStackTrace()
        } catch(e: JSONException) {
            e.printStackTrace()
        }
        Log.e("名前",name)
        return  name  // （12）





    }

    fun run(url: String): String {
        val request = Request.Builder().url(url).build()

        val response = client.newCall(request).execute()
        return response.body().string()
    }
}

