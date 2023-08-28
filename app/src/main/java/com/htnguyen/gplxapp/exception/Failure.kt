package com.htnguyen.gplxapp.exception

sealed class Failure: Throwable() {
    class NetworkConnection : Failure()

    class OtherError: Failure()
}