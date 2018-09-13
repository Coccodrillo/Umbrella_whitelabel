package org.secfirst.umbrella.whitelabel.feature.lesson

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhukic.sectionedrecyclerview.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.lesson_menu_head.view.*
import kotlinx.android.synthetic.main.lesson_menu_item.view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.database.content.Category

class MenuLessonAdapter(private val onclickHeader: (Category) -> Unit,
                        private val onclickLesson: (Category) -> Unit)
    : SectionedRecyclerViewAdapter<MenuLessonAdapter.SubHeadHolder, MenuLessonAdapter.LessonHolder>() {

    override fun onPlaceSubheaderBetweenItems(position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val categories = mutableListOf<Category>()

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
        val position = subheaderHolder.adapterPosition
        subheaderHolder.bind(categories[position], clickListener = { onclickHeader(categories[position]) })
    }

    fun addAll(categories: List<Category>) {
        categories.forEach { category -> this.categories.add(category) }
    }

    override fun getItemSize() = categories.size

    class SubHeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, clickListener: (SubHeadHolder) -> Unit) {
            with(category) {
                itemView.subheaderText.text = title
                itemView.setOnClickListener { clickListener(this@SubHeadHolder) }
            }
        }
    }

    class LessonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, clickListener: (LessonHolder) -> Unit) {
            with(category) {
                itemView.categoryName.text = title
                itemView.setOnClickListener { clickListener(this@LessonHolder) }
            }
        }
    }
}