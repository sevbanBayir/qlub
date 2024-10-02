package com.sevban.common.helper

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun timerFlow(delay: Duration): Flow<Duration> {
        return flow {
            var lastEmitTime = System.currentTimeMillis()
            emit(Duration.ZERO)

            while (true) {
                delay(delay)

                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - lastEmitTime

                emit(elapsedTime.milliseconds)
                lastEmitTime = currentTime
            }
        }
    }