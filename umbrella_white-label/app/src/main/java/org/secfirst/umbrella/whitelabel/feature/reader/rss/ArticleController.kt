package org.secfirst.umbrella.whitelabel.feature.reader.rss

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.einmalfel.earl.Item
import kotlinx.android.synthetic.main.host_article_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.component.WebViewController
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.reader.rss.adapter.ArticleCardAdapter

class ArticleController(bundle: Bundle) : BaseController(bundle) {

    private val onClickOpenArticle: (Item) -> Unit = this::onClickLearnMore
    private val rss by lazy { args.getSerializable(EXTRA_RSS) as RSS }

    constructor(rss: RSS) : this(Bundle().apply {
        putSerializable(EXTRA_RSS, rss)
    })

    companion object {
        const val EXTRA_RSS = "url"
    }

    override fun onInject() {

    }

    override fun onAttach(view: View) {
        init()
        disableNavigation()
        super.onAttach(view)
    }

    override fun onDestroyView(view: View) {
        disableToolbar()
        enableNavigation()
        super.onDestroyView(view)
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(activity)
        val itemDecor = DividerItemDecoration(activity, layoutManager.orientation)
        val articleAdapter = ArticleCardAdapter(onClickOpenArticle)
        articleAdapter.addAll(rss)
        recyclerViewArticle?.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = articleAdapter
            it.removeItemDecoration(itemDecor)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_article_view, container, false)
    }

    private fun onClickLearnMore(item: Item) {
        if (item.link != null && Patterns.WEB_URL.matcher(item.link).matches()) {
            router.pushController(RouterTransaction.with(WebViewController(item.link!!)))
        }
    }

    override fun getEnableBackAction() = true

    override fun getTitleToolbar() = "Umbrella"

}

