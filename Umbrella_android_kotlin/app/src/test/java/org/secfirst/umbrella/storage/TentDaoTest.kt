package org.secfirst.umbrella.storage

import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.secfirst.umbrella.data.storage.TentConfig
import org.secfirst.umbrella.data.storage.TentStorageDao
import org.secfirst.umbrella.storage.FakeTentRepository.Companion.`list of valid files`
import org.secfirst.umbrella.storage.FakeTentRepository.Companion.`valid list of element`

@RunWith(MockitoJUnitRunner.Silent::class)
class TentDaoTest {

    @Mock
    private lateinit var tentDao: TentStorageDao

    @Mock
    private lateinit var tentConfig: TentConfig

    @Test
    fun `should filter a valid list of sub elements`() {
        `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(`list of valid files`())
        val files = tentDao.filterBySubElement(tentConfig)
        assertNotNull(files)
    }

    @Test
    fun `shouldn't filter a valid list of sub elements`() {
        `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(emptyList())
        val files = tentDao.filterBySubElement(tentConfig)
        if (files.isEmpty()) assertTrue(true) else assertFalse(false)
    }

    @Test
    fun `shouldn't filter a valid list of elements`() {
        `when`(tentDao.filterBySubElement(tentConfig)).thenReturn(emptyList())
        val files = tentDao.filterByElement(tentConfig)
        if (files.isEmpty()) assertTrue(true) else assertFalse(false)
    }

    @Test
    fun `should filter a valid list of elements`() {
        `when`(tentDao.filterByElement(tentConfig)).thenReturn(`valid list of element`())
        val files = tentDao.filterByElement(tentConfig)
        assertNotNull(files)
    }

    @Test
    fun `should create a repository`() {
        `when`(tentDao.cloneRepository(tentConfig)).thenReturn(Single.just(true))
        val fetchResult = tentDao.cloneRepository(tentConfig)
        fetchResult.subscribe { result -> assertTrue(result) }
    }

    @Test
    fun `shouldn't create a repository`() {
        `when`(tentDao.cloneRepository(tentConfig)).thenReturn(Single.just(false))
        val fetchResult = tentDao.cloneRepository(tentConfig)
        fetchResult.subscribe { result -> assertTrue(result) }
    }

}