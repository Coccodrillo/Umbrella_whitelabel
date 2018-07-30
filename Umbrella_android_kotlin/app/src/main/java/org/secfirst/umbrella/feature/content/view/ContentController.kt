package org.secfirst.umbrella.feature.content.view

import Extensions
import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_standard.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.database.AppDatabase
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.content.DaggerContentComponent
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.presenter.ContentBasePresenter
import javax.inject.Inject


class ContentController : BaseController(), ContentBaseView {

    @Inject
    internal lateinit var presenter: ContentBasePresenter<ContentBaseView, ContentBaseInteractor>

    override fun getTitleToolbar() = applicationContext!!.getString(R.string.checklist_title)!!
    override fun getEnableBackAction() = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.fragment_standard, container, false)
        val exportDb = view.findViewById<Button>(R.id.test)
        exportDb.setOnClickListener { showFileChooserPreview() }
        presenter.onAttach(this)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        firstAccess.setOnClickListener {
            presenter.manageContent()
            it.isEnabled = false
        }
    }

    override fun onInject() {
        DaggerContentComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onErrorContentNotFound() {
        Toast.makeText(activity, "Content not found", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): ContentController = ContentController()
    }

    override fun finishDownloadedData() {
        Toast.makeText(activity!!, "Downloaded with success", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                showFileChooserPreview()
            } else {
                // Permission request was denied.
            }
        }
    }

    private fun showFileChooserPreview() {
        if (ContextCompat.checkSelfPermission(activity!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            shareDbFile(AppDatabase.NAME)
        } else {
            requestExternalStoragePermission()
        }

    }

    private fun requestExternalStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            // Request the permission
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_EXTERNAL_STORAGE)
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_EXTERNAL_STORAGE)
        }
    }

    private fun shareDbFile(fileName: String) {
        Extensions.copyFile()
    }

    val PERMISSION_REQUEST_EXTERNAL_STORAGE = 1
}