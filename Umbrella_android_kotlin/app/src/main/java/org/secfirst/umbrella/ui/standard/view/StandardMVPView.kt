package org.secfirst.umbrella.ui.standard.view

import org.secfirst.umbrella.data.remote.Blog
import org.secfirst.umbrella.ui.base.view.MVPView

interface StandardMVPView : MVPView {

    fun displayBlogList(blogs: List<Blog>?): Unit?

}