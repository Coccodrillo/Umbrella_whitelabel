package org.secfirst.whitelabel.feature.tour.view

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.tour_view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.secfirst.whitelabel.R
import org.secfirst.whitelabel.UmbrellaApplication
import org.secfirst.whitelabel.component.DialogManager
import org.secfirst.whitelabel.component.DialogManager.Companion.PROGRESS_DIALOG_TAG
import org.secfirst.whitelabel.feature.MainActivity
import org.secfirst.whitelabel.feature.base.view.BaseController
import org.secfirst.whitelabel.feature.lesson.LessonController
import org.secfirst.whitelabel.feature.main.OnNavigationBottomView
import org.secfirst.whitelabel.feature.tour.DaggerTourComponent
import org.secfirst.whitelabel.feature.tour.interactor.TourBaseInteractor
import org.secfirst.whitelabel.feature.tour.presenter.TourBasePresenter
import javax.inject.Inject


class TourController : BaseController(), TourView {
    @Inject
    internal lateinit var presenter: TourBasePresenter<TourView, TourBaseInteractor>
    private var viewList: MutableList<TourUI> = mutableListOf()
    private lateinit var nav: OnNavigationBottomView
    private lateinit var dialogManager: DialogManager
    private lateinit var progressDialog: ProgressDialog

    override fun onInject() {
        DaggerTourComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    init {
        viewList.add(TourUI(R.color.umbrella_purple_dark, R.drawable.umbrella190, R.string.tour_slide_1_text, VISIBLE, GONE))
        viewList.add(TourUI(R.color.umbrella_green, R.drawable.walktrough2, R.string.tour_slide_2_text, VISIBLE, GONE))
        viewList.add(TourUI(R.color.umbrella_yellow, R.drawable.walktrough3, R.string.tour_slide_3_text, VISIBLE, GONE))
        viewList.add(TourUI(R.color.umbrella_purple, R.drawable.walktrough4, R.string.terms_conditions, GONE, VISIBLE))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        dialogManager = DialogManager(this)
        return inflater.inflate(R.layout.tour_view, container, false)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        hideToolbarAndNav()
        initViewPager()
        onAcceptButton()
        presenter.onAttach(this)
    }

    override fun downloadContentCompleted(res: Boolean) {
        progressDialog.dismiss()
        if (res) {
            router.pushController(RouterTransaction.with(LessonController()))
            nav.showBottomMenu()
            nav.showToolbar()
        } else {
            dialogManager.showDialog(object : DialogManager.DialogFactory {
                override fun createDialog(context: Context?): Dialog {
                    return AlertDialog.Builder(context)
                            .setMessage(context?.getString(R.string.error_connection_tour_message))
                            .setPositiveButton("Cool") { _, _ -> presenter.manageContent() }
                            .setNegativeButton("Nah") {dialog, _ ->  dialog.dismiss()}
                            .setCancelable(false)
                            .show()
                }
            })
        }
    }

    override fun downloadContentInProgress() {
        view?.let {
            doLongOperation()
        }
    }

    private fun onAcceptButton() {
        acceptButton?.let { btn ->
            btn.onClick {
                presenter.manageContent()
            }
        }
    }

    private fun hideToolbarAndNav() {
        nav = activity as MainActivity
        nav.hideBottomMenu()
        nav.hideToolbar()
    }


    private fun doLongOperation() {
        dialogManager.showDialog(PROGRESS_DIALOG_TAG, object : DialogManager.DialogFactory {
            override fun createDialog(context: Context?): Dialog {
                progressDialog = ProgressDialog(context)
                progressDialog.setCancelable(false)
                progressDialog.setMessage(context?.getString(R.string.loading_tour_message))
                return progressDialog
            }
        })
    }

    private fun initViewPager() {
        tourViewPager?.let {
            val tourAdapter = TourAdapter(this)
            it.adapter = tourAdapter
            tourAdapter.setData(viewList)
            it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    if (position == viewList.lastIndex)
                        acceptButton?.let { btn -> btn.visibility = VISIBLE }
                    else
                        acceptButton?.let { btn -> btn.visibility = INVISIBLE }
                }
            })
        }
    }
}