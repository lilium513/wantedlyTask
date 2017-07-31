package com.liliumproject.android.wantedlytask

import android.text.TextUtils
import android.support.v4.widget.SearchViewCompat.setQueryHint
import android.support.v4.widget.SearchViewCompat.setSubmitButtonEnabled
import android.os.Bundle
import android.app.Activity



import android.support.v4.widget.SearchViewCompat.setQueryHint
import android.support.v4.widget.SearchViewCompat.setSubmitButtonEnabled
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.util.Log
import android.widget.*
import com.liliumproject.android.wantedlytask.R
import java.util.logging.Logger

class MainActivity : Activity(), SearchView.OnQueryTextListener {
     private var array_adapter_data = arrayOf("")
     var adapter:ArrayAdapter<String>? = null
    var temp:String =""
    var a:ApiProceeding? = null
    var list:ListView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById(R.id.b) as Button

        val search = findViewById(R.id.search) as SearchView
        list = findViewById(R.id.re) as ListView
        a=ApiProceeding(list!!,this)
        array_adapter_data= a!!.names.toTypedArray()
        adapter =ArrayAdapter(this,
                android.R.layout.simple_list_item_1, array_adapter_data)
        list!!.setAdapter(adapter)
        list!!.setTextFilterEnabled(true)
        // SearchViewの初期表示状態を設定
        search.setIconifiedByDefault(false)
        search.setOnQueryTextListener(this)
        // SearchViewにOnQueryChangeListenerを設定

        // SearchViewのSubmitボタンを使用不可にする
        search.setSubmitButtonEnabled(true)

        // SearchViewに何も入力していない時のテキストを設定
        search.setQueryHint("検索文字を入力して下さい。")
        btn.setOnClickListener { a!!.execute("tetris")

            array_adapter_data=a!!.names.toTypedArray()

            adapter =ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, array_adapter_data)
            adapter!!.notifyDataSetChanged()
            adapter!!.notifyDataSetChanged()

            list!!.setAdapter(adapter)
            adapter!!.notifyDataSetChanged()
            Log.e("end","end")

        }
    }

     override fun onQueryTextChange(newText: String?): Boolean {
         if (TextUtils.isEmpty(newText)) {
         } else if(newText?.length?.rem(2) ==0){
             a=ApiProceeding(list!!,this)
             a?.execute(newText)

         }
         return true;

     }
     override fun onQueryTextSubmit(query: String?): Boolean {
         return false
     }

}