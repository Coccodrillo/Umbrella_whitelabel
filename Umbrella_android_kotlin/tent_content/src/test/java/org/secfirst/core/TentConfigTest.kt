package org.secfirst.core

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.secfirst.core.storage.TentStorageDao
import org.secfirst.core.utils.ExtensionFile
import org.secfirst.core.utils.TentConfig
import org.secfirst.core.utils.TentConfig.Companion.HIERARCHY_ELEMENT
import org.secfirst.core.utils.TentConfig.Companion.HIERARCHY_SUB_ELEMENT
import org.secfirst.core.utils.TentConfig.Companion.HIERARCHY_SUB_SUB_ELEMENT
import org.secfirst.core.utils.TypeFile

@RunWith(MockitoJUnitRunner::class)
class TentConfigTest {
    private val tentDao
        get() = object : TentStorageDao {}

    @Mock
    private lateinit var tentConfig: TentConfig

    @Test
    fun categoryValidDelimiter() {
        val delimiter = TentConfig.getDelimiter(TentConfig.getDelimiter(".category.yml"))
        assertEquals(delimiter, ".category.yml")
    }

    @Test
    fun segmentValidDelimiter() {
        val delimiter = TentConfig.getDelimiter(TentConfig.getDelimiter("s_something.yml"))
        assertEquals(delimiter, "s")
    }

    @Test
    fun checklistValidDelimiter() {
        val delimiter = TentConfig.getDelimiter(TentConfig.getDelimiter("c_checklist.yml"))
        assertEquals(delimiter, "c")
    }

    @Test
    fun prefixOfCategory() {
        assertEquals(TypeFile.CATEGORY.value, ".category")
    }

    @Test
    fun prefixOfForm() {
        assertEquals(TypeFile.FORM.value, "f")
    }


    @Test
    fun prefixOfChecklist() {
        assertEquals(TypeFile.CHECKLIST.value, "c")
    }

    @Test
    fun prefixOfSegment() {
        assertEquals(TypeFile.SEGMENT.value, "s")
    }

    @Test
    fun ymlExtension() {
        assertEquals(ExtensionFile.YML.value, "yml")
    }


    @Test
    fun mdlExtension() {
        assertEquals(ExtensionFile.MD.value, "md")
    }

    @Test
    fun isTentRepository() {
        Mockito.`when`(tentConfig.isNotRepositoryPath()).thenReturn(true)
        val value = tentConfig.isNotRepositoryPath()
        assertEquals(true, value)
    }

    @Test
    fun isNotTentRepository() {
        Mockito.`when`(tentConfig.isNotRepositoryPath()).thenReturn(false)
        val value = tentConfig.isNotRepositoryPath()
        assertEquals(false, value)
    }

    @Test
    fun getTentPathOfRepository() {
        Mockito.`when`(tentConfig.getPathRepository()).thenReturn("/path/")
        val value = tentConfig.getPathRepository()
        assertEquals("/path/", value)
    }

    @Test
    fun formValidDelimiter() {
        val delimiter = TentConfig.getDelimiter(TentConfig.getDelimiter("f_form.yml"))
        assertEquals(delimiter, "f")
    }

    @Test
    fun invalidFileName() {
        val delimiter = TentConfig.getDelimiter(TentConfig.getDelimiter("something.unknown"))
        assertEquals(delimiter, "something.unknown")
    }

    @Test
    fun hierarchyOfCategory() {
        assertEquals(HIERARCHY_ELEMENT, 1)
    }

    @Test
    fun hierarchyOfSubcategory() {
        assertEquals(HIERARCHY_SUB_ELEMENT, 2)
    }

    @Test
    fun hierarchyOfSubSubcategory() {
        assertEquals(HIERARCHY_SUB_SUB_ELEMENT, 3)
    }
}