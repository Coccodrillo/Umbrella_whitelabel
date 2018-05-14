package org.secfirst.umbrella.ui.standard.view

import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.network.Blog
import org.secfirst.umbrella.ui.base.view.BaseView

interface StandardBaseView : BaseView {

    fun displayBlogList(blogs: List<Blog>?)

    fun displayData(standard: List<Standard>)
}
