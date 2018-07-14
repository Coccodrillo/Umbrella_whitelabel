import android.content.Context
import android.util.Log
import org.secfirst.umbrella.UmbrellaApplication
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class Extensions {
    companion object {
        @Throws(IOException::class)
        fun copyFile() {
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
                Log.i("test", "DB dump OK")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("test", "DB dump ERROR")
            } finally {
                try {
                    fos!!.close()
                    fis!!.close()
                } catch (ioe: IOException) {
                }

            }
        }
    }
}

val Context.myApp: UmbrellaApplication
    get() = applicationContext as UmbrellaApplication