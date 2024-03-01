package com.example.a.retromock

import co.infinum.retromock.BodyFactory
import java.io.FileInputStream
import java.io.InputStream

class ResourceBodyFactory : BodyFactory {
    override fun create(input: String): InputStream {
        return FileInputStream(ResourceBodyFactory().javaClass.classLoader.getResource(input).file)
    }
}