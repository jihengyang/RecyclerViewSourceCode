package com.tencent.news.recyclerviewsourcecode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var listAdapter: RecyclerView.Adapter<CustomViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<RecyclerView>(R.id.list_view)

        findViewById<View>(R.id.refresh_btn).apply {
            setOnClickListener {
                listView.scrollToPosition(3)
            }
        }

        findViewById<View>(R.id.single_refresh_btn).apply {
            setOnClickListener {
                listAdapter?.notifyItemChanged(2)
            }
        }

        findViewById<RecyclerView>(R.id.list_view).apply {
            itemAnimator = DefaultItemAnimator()

            layoutManager = LinearLayoutManager(context)

            listAdapter = CustomListAdapter(context).apply {
                for (i in 1 until 20) {
                    dataList.add(i)
                }
            }
            adapter = listAdapter

            listAdapter?.notifyDataSetChanged()
        }
    }

    class CustomListAdapter(val context: Context) : RecyclerView.Adapter<CustomViewHolder>() {
        var dataList: ArrayList<Int> = ArrayList()

        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): CustomViewHolder = CustomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.test_cell, parent, false).apply {
                }
        )

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.custom_position = position
            holder.itemView.findViewById<TextView>(R.id.title).text = "测试标题${dataList[position]}"
            holder.itemView.findViewById<View>(R.id.remove).setOnClickListener {
                dataList.remove(position)
                notifyDataSetChanged()
            }
//            log("onBindViewHolder, position:$position")
        }

        override fun getItemCount(): Int = dataList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var custom_position: Int = 0
    }

    private fun log(logStr: String) {
        Log.i("MainActivity-log", logStr)
    }
}