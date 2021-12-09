package hu.bme.aut.executor.feature.uploads.service

import hu.bme.aut.executor.domain.Upload
import hu.bme.aut.executor.feature.uploads.dto.UploadDetailsResponse
import hu.bme.aut.executor.repository.LabelRepository

import hu.bme.aut.executor.repository.UploadRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.OffsetDateTime

import java.util.*

@SpringJUnitConfig
class UploadServiceImplTest {

    @Mock
    lateinit var uploadRepository: UploadRepository
    @Mock
    lateinit var labelRepository: LabelRepository
    @InjectMocks
    lateinit var uploadService: UploadServiceImpl

    @Test
    fun testGetUploadReturnsUploadDetails() {
        val mockId = 1L
        val mockUserId = 1L
        val mockUpload = getMockUpload()
        `when`(uploadRepository.findById(mockId))
            .thenReturn(Optional.of(mockUpload))

        val uploadDetails = uploadService.getUpload(mockUserId, mockId)

        assertUploadDetails(mockUpload, uploadDetails)
    }

    private fun assertUploadDetails(mockUpload: Upload, uploadDetails: UploadDetailsResponse) {
        assertEquals(mockUpload.id, uploadDetails.id)
        assertEquals(mockUpload.name, uploadDetails.name)
        assertEquals(mockUpload.description, uploadDetails.description)
        assertEquals(mockUpload.text, uploadDetails.text)
        assertEquals(mockUpload.userId, uploadDetails.userId)
        assertEquals(mockUpload.createdAt, uploadDetails.createdAt)
    }

    private fun getMockUpload() = Upload(
        id = 1L,
        name = "mockUpload",
        description = "mockDescription",
        text = "mockText",
        userId = 1L
    ).apply { createdAt = OffsetDateTime.now() }

}