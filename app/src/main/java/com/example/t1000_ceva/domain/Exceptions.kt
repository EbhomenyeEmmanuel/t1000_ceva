package com.example.t1000_ceva.domain

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)