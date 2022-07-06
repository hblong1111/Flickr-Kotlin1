package com.example.flickrkotlin.repository

import org.junit.Assert.*

import org.junit.Test

class PhotoRepositoryTest {

    @Test
    fun calculatorDayWithSize() {
        assertEquals(PhotoRepository.calculatorDayWithSize(501), 1)
    }

    @Test
    fun calculatorDayWithSize1() {
        assertEquals(PhotoRepository.calculatorDayWithSize(998), 1)
    }
}