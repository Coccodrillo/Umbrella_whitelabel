package org.secfirst.umbrella.feature.content.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.database.AppDatabase
import org.secfirst.umbrella.data.database.content.New
import org.secfirst.umbrella.feature.base.view.BaseFragment
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.presenter.ContentBasePresenter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


class ContentFragment : BaseFragment(), ContentBaseView {

    @Inject
    internal lateinit var presenter: ContentBasePresenter<ContentBaseView, ContentBaseInteractor>
    val PERMISSION_REQUEST_EXTERNAL_STORAGE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_standard, container, false)
        val exportDb = view.findViewById<Button>(R.id.test)
        exportDb.setOnClickListener { showFileChooserPreview() }
        return view
    }

    companion object {
        fun newInstance(): ContentFragment = ContentFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setUp() {
        presenter.manageContent()
        //presenter.validateLoadAllLesson()
    }

    override fun downloadContent(lesson: New) {
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
        copyFile()
    }

    @Throws(IOException::class)
    private fun copyFile() {
        val f = File("/data/data/org.secfirst.umbrella/databases/AppDatabase.db")
        var fis: FileInputStream? = null
        var fos: FileOutputStream? = null

        try {
            fis = FileInputStream(f)
            fos = FileOutputStream("/mnt/sdcard/db_dump.db")
            while (true) {
                val i = fis.read()
                if (i != -1) {
                    fos.write(i)
                } else {
                    break
                }
            }
            fos.flush()
            Toast.makeText(context, "DB dump OK", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "DB dump ERROR", Toast.LENGTH_LONG).show()
        } finally {
            try {
                fos!!.close()
                fis!!.close()
            } catch (ioe: IOException) {
            }

        }
    }
}