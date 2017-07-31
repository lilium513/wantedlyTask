package com.liliumproject.android.wantedlytask

import android.R
import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
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
import android.widget.TextView



/**
 * Created by kt on 2017/07/31.
 */
public open class ApiProceeding(val li:ListView,val co:Context) : AsyncTask<String,Void,Array<String>>()  {
    open var client = OkHttpClient()
    open var names: MutableList<String> = mutableListOf()
    var list:ListView
    var adapter:ArrayAdapter<String>? = null
    var context:Context
   init {
        list = li
       context=co

   }


    override fun doInBackground(vararg p0: String?): Array<String> {
        val q: String? = p0[0]
        var url: String = "https://api.github.com/search/repositories?q="+p0[0]
        var res: String = ""
        var name: String =""

        try {
            res = run(url)
            val resJson = JSONObject(res)
            val reps = resJson.getJSONArray("items")
            name =reps.getJSONObject(0).getString("name")
            for (i in 0 until reps.length()){
                names.add(reps.getJSONObject(i).getString("name"))
                Log.e("名前",names.get(i))

            }

                    } catch(e: IOException) {
            e.printStackTrace()
        } catch(e: JSONException) {
            e.printStackTrace()
        }
        Log.e("名前",name)
        return  names.toTypedArray()  // （12）





    }

    fun run(url: String): String {
        val request = Request.Builder().url(url).build()

        val response = client.newCall(request).execute()
        return response.body().string()
    }

    override fun onPostExecute(result: Array<String>){
        adapter= ArrayAdapter(context,android.R.layout.simple_list_item_1,names)
        list!!.setAdapter(adapter)
    }




    }



