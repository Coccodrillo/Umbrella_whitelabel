package org.secfirst.umbrella.whitelabel.feature.lesson.view

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.lesson_menu_head.view.*
import kotlinx.android.synthetic.main.lesson_menu_item.view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.database.content.Category


class LessonMenuAdapter(groups: List<ExpandableGroup<*>>,
                        private val onclickLesson: (Category?) -> Unit,
                        private val onclickHeader: (Category?) -> Unit)
    : ExpandableRecyclerViewAdapter<LessonMenuAdapter.HeadHolder, LessonMenuAdapter.LessonMenuHolder>(groups) {


    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): HeadHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.lesson_menu_head, parent, false)
        return HeadHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): LessonMenuHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.lesson_menu_item, parent, false)
        return LessonMenuHolder(view)
    }

    override fun onBindChildViewHolder(holder: LessonMenuHolder, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int) {
        val itemGroup = (group as ItemSection).items[childIndex]
        holder.bind(itemGroup.title, clickListener = { onclickLesson(null) })
    }

    override fun onBindGroupViewHolder(holder: HeadHolder, flatPosition: Int, group: ExpandableGroup<*>) {
        holder.setHeadTitle(group)
    }

    class HeadHolder(itemView: View) : GroupViewHolder(itemView) {
        fun setHeadTitle(group: ExpandableGroup<*>) {
            itemView.subheaderText.text = group.title
        }
    }

    class LessonMenuHolder(itemView: View) : ChildViewHolder(itemView) {
        fun bind(titleSection: String, clickListener: (LessonMenuHolder) -> Unit) {
            itemView.categoryName.text = titleSection
            itemView.setOnClickListener { clickListener(this) }
        }
    }
    class ItemSection(title: String, items: List<ItemGroup>) : ExpandableGroup<ItemGroup>(title, items)
    @Parcelize
    class ItemGroup(val title: String, val idSubcategory: Long) : Parcelable
}
