package com.example.flickrkotlin.repository

import com.example.flickrkotlin.utils.DateUtils
import org.junit.Assert.*

import org.junit.Test

class PhotoRepositoryTest {

    @Test
    fun calculatorDayWithSize() {
        assertEquals(PhotoRepository.calculatorDayWithSize(501), 1)
    }

    @Test
    fun calculatorDayWithSize1() {
        assertEquals(PhotoRepository.calculatorDayWithSize(500), 1)
        assertEquals(DateUtils.getDateString(PhotoRepository.calculatorDayWithSize(501)), "0")
    }
}