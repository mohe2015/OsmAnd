package net.osmand.shared.extensions

import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()
fun millisToLocalDateTime(milliseconds: Long): LocalDateTime =
	Instant.fromEpochMilliseconds(milliseconds).toLocalDateTime(TimeZone.currentSystemDefault())
