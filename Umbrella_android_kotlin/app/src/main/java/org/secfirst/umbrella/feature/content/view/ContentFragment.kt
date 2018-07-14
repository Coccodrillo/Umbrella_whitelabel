package org.secfirst.umbrella.feature.content.view

import Extensions
import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.database.AppDatabase
import org.secfirst.umbrella.data.database.content.Lesson
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.content.DaggerContentComponent
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.presenter.ContentBasePresenter
import javax.inject.Inject


class ContentFragment : BaseController(), ContentBaseView {

    @Inject
    internal lateinit var presenter: ContentBasePresenter<ContentBaseView, ContentBaseInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.fragment_standard, container, false)
        val exportDb = view.findViewById<Button>(R.id.test)
        exportDb.setOnClickListener { showFileChooserPreview() }
        presenter.onAttach(this)
        presenter.manageContent()
        return view
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
        fun newInstance(): ContentFragment = ContentFragment()
    }

    override fun downloadContent(lesson: Lesson) {
        lesson.categories.forEach { Log.e("test", "object -  $it") }
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

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val PERMISSION_REQUEST_EXTERNAL_STORAGE = 1
}