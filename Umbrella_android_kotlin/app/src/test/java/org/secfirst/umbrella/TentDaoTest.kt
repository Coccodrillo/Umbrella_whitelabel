package org.secfirst.umbrella

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.secfirst.umbrella.data.storage.TentConfig
import org.secfirst.umbrella.data.storage.TentStorageDao
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class TentDaoTest {

    @Mock
    private lateinit var tentDao: TentStorageDao

    @Mock
    private lateinit var tentConfig: TentConfig


    private fun randomicLisOfFiles(): List<File> {
        val file1 = File("/travel/kidnapping/beginner/close.png")
        val file2 = File("/travel/.foreingkey.yml")
        val file3 = File("/travel/kidnapping/beginner/.foreingkey.yml")
        val file4 = File("/travel/kidnapping/beginner/c_checklist.yml")
        val file5 = File("/travel/kidnapping/.foreingkey.yml")
        val file6 = File("/travel/kidnapping/intermediate/.foreingkey.yml")
        val file7 = File("/travel/kidnapping/intermediate/s_segments.yml")
        val file8 = File("/travel/kidnapping/advanced/.foreingkey.yml")
        val file9 = File("/email/how_to_learn.md")
        val file10 = File("/about/.foreingkey.yml")
        val file11 = File("/something/hello.xml")
        val file12 = File("/form/f_first_form.yml")

        val files = arrayListOf<File>()
        files.add(file1)
        files.add(file2)
        files.add(file3)
        files.add(file4)
        files.add(file5)
        files.add(file6)
        files.add(file7)
        files.add(file8)
        files.add(file9)
        files.add(file10)
        files.add(file11)
        files.add(file12)
        return files
    }

    private fun validFileOfElement(): List<File> {
        val files = arrayListOf<File>()
        val file1 = File("/about/.foreingkey.yml")
        val file2 = File("/travel/.foreingkey.yml")
        val file3 = File("/travel/kidnapping/.foreingkey.yml")
        val file4 = File("/travel/kidnapping/beginner/.foreingkey.yml")
        val file5 = File("/travel/kidnapping/intermediate/.foreingkey.yml")
        val file6 = File("/travel/kidnapping/advanced/.foreingkey.yml")

        files.add(file1)
        files.add(file2)
        files.add(file3)
        files.add(file4)
        files.add(file5)
        files.add(file6)
        return files
    }

    private fun validFilesOfSubElement(): List<File> {

        val file1 = File("/travel/kidnapping/intermediate/s_segments.yml")
        val file2 = File("/travel/kidnapping/beginner/c_checklist.yml")
        val file3 = File("/form/f_first_form.yml")

        val files = arrayListOf<File>()
        files.add(file1)
        files.add(file2)
        files.add(file3)

        return files
    }

    @Test
    fun filterSubElements() {
        Mockito.`when`(tentDao.filterBySubElement(tentConfig)).thenReturn(validFilesOfSubElement())
        val files = tentDao.filterBySubElement(tentConfig)
        val numberOfFileFiltered = randomicLisOfFiles().size - validFilesOfSubElement().size
        Assert.assertEquals(files.size, randomicLisOfFiles().size - numberOfFileFiltered)
    }

    @Test
    fun filterElements() {
        Mockito.`when`(tentDao.filterByElement(tentConfig)).thenReturn(validFileOfElement())
        val files = tentDao.filterByElement(tentConfig)
        val numberOfFileFiltered = randomicLisOfFiles().size - validFileOfElement().size
        Assert.assertEquals(files.size, numberOfFileFiltered)
    }
}