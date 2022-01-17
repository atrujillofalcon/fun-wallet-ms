package es.atrujillo.sample.funwalletms.config.extensions

import org.slf4j.LoggerFactory

fun Any.logDebug(msg: String) {
    LoggerFactory.getLogger(this::class.java).debug(msg)
}

fun Any.logInfo(msg: String) {
    LoggerFactory.getLogger(this::class.java).info(msg)
}

fun Any.logWarn(msg: String) {
    LoggerFactory.getLogger(this::class.java).warn(msg)
}

fun Any.logError(msg: String) {
    LoggerFactory.getLogger(this::class.java).error(msg)
}

fun Any.logError(msg: String, e: Throwable) {
    LoggerFactory.getLogger(this::class.java).error(msg, e)
}