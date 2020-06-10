package com.example.android.navigationadvancedsample.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cn.edu.scujcc.duoduo.R
import com.example.android.navigationadvancedsample.R


/**
 *
显示包含多个用户的静态排行榜。
 */


class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //为每个数据项提供对视图的引用
    // 复杂的数据项可能需要每个项有多个视图，并且
    // 提供对视图保持器中数据项的所有视图的访问。
    // 在本例中，每个数据项都只是文本视图中显示的字符串。
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)


    // 创建新视图（由布局管理器调用）
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        // 创建新视图
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_item, parent, false)


        return ViewHolder(itemView)
    }

    // 替换视图的内容（由布局管理器调用）
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - 在此位置从数据集中获取元素
        // - 用该元素替换视图的内容
        holder.item.findViewById<TextView>(R.id.user_name_text).text = myDataset[position]

        holder.item.findViewById<ImageView>(R.id.user_avatar_image)
                .setImageResource(listOfAvatars[position % listOfAvatars.size])

        holder.item.setOnClickListener {
            val bundle = bundleOf(USERNAME_KEY to myDataset[position])

            holder.item.findNavController().navigate(
                    R.id.action_leaderboard_to_userProfile,
                bundle)
        }
    }

    // 返回数据集的大小（由布局管理器调用）
    override fun getItemCount() = myDataset.size

    companion object {
        const val USERNAME_KEY = "userName"
    }
}

private val listOfAvatars = listOf(
    R.drawable.avatar_1_raster
)
