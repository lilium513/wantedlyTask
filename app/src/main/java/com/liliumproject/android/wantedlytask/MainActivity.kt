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

 class MainActivity : Activity(), SearchView.OnQueryTextListener {
     private val list: ListView? = null
     private val array_adapter_data = arrayOf("Apple", "Bike", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb")
     var adapter:ArrayAdapter<String>? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById(R.id.b) as Button
        btn.setOnClickListener { object: ApiProceeding(){}.execute("tetris") }
        val search = findViewById(R.id.search) as SearchView
        val list = findViewById(R.id.re) as ListView
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
    }

     override fun onQueryTextChange(newText: String?): Boolean {
         if (TextUtils.isEmpty(newText)) {
             list?.clearTextFilter()
         } else {
             list?.setFilterText(newText.toString());
             Log.d("else",newText.toString())
             adapter?.notifyDataSetChanged()

         }
         return true;

     }
     override fun onQueryTextSubmit(query: String?): Boolean {
         return false
     }

}