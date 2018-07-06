package org.secfirst.umbrella

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.secfirst.umbrella.feature.content.interactor.ContentBaseInteractor
import org.secfirst.umbrella.feature.content.presenter.ContentPresenterImp
import org.secfirst.umbrella.feature.content.view.ContentBaseView
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class ContentInteractorTest {

    @Mock
    private lateinit var contentInteractor: ContentPresenterImp<ContentBaseView, ContentBaseInteractor>

    private val emptyRepository: List<File> = arrayListOf()

    @Test(expected = Throwable::class)
    fun `should get a null point when tent repository files is null or empty`() {
        Mockito.`when`(contentInteractor.manageContent())
                .thenThrow(Throwable("Content not found."))

        contentInteractor.manageContent()
    }
}