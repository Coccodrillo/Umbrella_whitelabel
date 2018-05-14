package org.secfirst.umbrella

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.secfirst.umbrella.data.database.standard.Standard

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_testando() {
        assertEquals(4, 2 + 2)
    }

    fun another_test() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun another_test2(){
        assertNotNull(Standard(1,"douglas"))
    }

}

