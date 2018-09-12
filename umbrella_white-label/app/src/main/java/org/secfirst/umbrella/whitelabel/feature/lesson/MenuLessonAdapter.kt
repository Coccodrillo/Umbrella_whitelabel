package org.secfirst.umbrella.whitelabel.feature.lesson

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.lesson_menu_head.view.*
import kotlinx.android.synthetic.main.lesson_menu_item.view.*
import org.secfirst.umbrella.whitelabel.data.database.content.Category
//
//abstract class MenuLessonAdapter : SectionedRecyclerViewAdapter<MenuLessonAdapter.LessonHolder>() {
//
//    class SubHeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(category: Category, clickListener: (SubHeadHolder) -> Unit) {
//            with(category) {
//                itemView.subheaderText.text = title
//                itemView.setOnClickListener { clickListener(this@SubHeadHolder) }
//            }
//        }
//    }
//
//    class LessonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(category: Category, clickListener: (LessonHolder) -> Unit) {
//            with(category) {
//                itemView.categoryName.text = title
//                itemView.setOnClickListener { clickListener(this@LessonHolder) }
//            }
//        }
//    }
//}