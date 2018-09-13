package org.secfirst.umbrella.whitelabel.feature.lesson.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhukic.sectionedrecyclerview.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.lesson_menu_head.view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.database.content.Category

class MenuLessonAdapter(private val onclickLesson: (Category) -> Unit,
                        private val onclickHeader: (Int) -> Unit)
    : SectionedRecyclerViewAdapter<MenuLessonAdapter.SubHeadHolder, MenuLessonAdapter.LessonHolder>() {

    private var categories: MutableList<Category> = mutableListOf()

    override fun onPlaceSubheaderBetweenItems(position: Int) = true

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_menu_item, parent, false)
        return LessonHolder(view)
    }

    override fun onCreateSubheaderViewHolder(parent: ViewGroup, viewType: Int): SubHeadHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_menu_head, parent, false)
        return SubHeadHolder(view)
    }

    override fun onBindItemViewHolder(holder: LessonHolder, itemPosition: Int) {
        holder.bind(categories[itemPosition], clickListener = { onclickLesson(categories[itemPosition]) })
    }

    override fun onBindSubheaderViewHolder(subheaderHolder: SubHeadHolder, nextItemPosition: Int) {
        subheaderHolder.bind(categories[nextItemPosition],
                isSectionExpanded(getSectionIndex(nextItemPosition)),
                clickListener = { onclickHeader(nextItemPosition) })
    }

    fun addAll(categories: List<Category>) {
        this.categories = categories.toMutableList()
        notifyDataSetChanged()
    }

    fun add(category: Category) {
        categories.add(category)
        notifyDataSetChanged()
    }

    override fun getItemSize() = categories.size

    class SubHeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, isSectionExpanded: Boolean, clickListener: (SubHeadHolder) -> Unit) {
            with(category) {
                itemView.subheaderText.text = title
                if (isSectionExpanded) {
                    itemView.arrow.setImageDrawable(ContextCompat.getDrawable(
                            itemView.context, R.drawable.ic_keyboard_arrow_up_black_24dp))
                } else {
                    itemView.arrow.setImageDrawable(ContextCompat.getDrawable(
                            itemView.context, R.drawable.ic_keyboard_arrow_down_black_24dp))
                }
                itemView.setOnClickListener { clickListener(this@SubHeadHolder) }
            }
        }
    }

    class LessonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, clickListener: (LessonHolder) -> Unit) {
            with(category) {
                itemView.setOnClickListener { clickListener(this@LessonHolder) }
            }
        }
    }
}