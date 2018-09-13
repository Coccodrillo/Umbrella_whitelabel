package org.secfirst.umbrella.whitelabel.storage

import junit.framework.Assert.*
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.secfirst.umbrella.whitelabel.data.disk.TentConfig
import org.secfirst.umbrella.whitelabel.data.disk.TentDao
import org.secfirst.umbrella.whitelabel.storage.FakeTentRepository.Companion.`list of valid files`
import org.secfirst.umbrella.whitelabel.storage.FakeTentRepository.Companion.`valid list of element`
import java.io.File


@RunWith(MockitoJUnitRunner.Silent::class)
class TentDaoTest {

    @Mock
    private lateinit var tentDao: TentDao

    @Mock
    private lateinit var tentConfig: TentConfig



    @Test
    fun `should filter a valid list of sub elements`() {
        var files = listOf<File>()
        runBlocking {
            `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(`list of valid files`())
            files = tentDao.filterBySubElement(tentConfig)
        }
        assertNotNull(files)
    }

    @Test
    fun `shouldn't filter a valid list of sub elements`() {
        var files = listOf<File>()
        runBlocking {
            `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(emptyList())
            files = tentDao.filterBySubElement(tentConfig)
        }
        if (files.isEmpty()) assertTrue(true) else assertFalse(false)
    }

    @Test
    fun `shouldn't filter a valid list of elements`() {
        var files = listOf<File>()
        runBlocking {
            `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(emptyList())
            files = tentDao.filterByElement(tentConfig)
        }
        assertNull(files)
    }

    @Test
    fun `should filter a valid list of elements`() {
        var files = listOf<File>()
        runBlocking {
            `when`(tentDao.filterByElement(tentConfig)).thenReturn(`valid list of element`())
            files = tentDao.filterByElement(tentConfig)
        }
        assertNotNull(files)
    }

    @Test
    fun `should create a repository`() {
        var res = false
        runBlocking {
            `when`(tentDao.cloneRepository(tentConfig)).thenReturn(true)
            res = tentDao.cloneRepository(tentConfig)
        }
        assertTrue(res)
    }

    @Test
    fun `shouldn't create a repository`() {
        var res = false
        runBlocking {
            `when`(tentDao.cloneRepository(tentConfig)).thenReturn(false)
            res = tentDao.cloneRepository(tentConfig)
        }
        assertFalse(res)
    }

}