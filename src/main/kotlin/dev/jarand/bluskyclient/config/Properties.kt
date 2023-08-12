package dev.jarand.bluskyclient.config

import org.apache.commons.configuration2.FileBasedConfiguration
import org.apache.commons.configuration2.YAMLConfiguration
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder
import org.apache.commons.configuration2.builder.fluent.Parameters

class Properties {

    private val configuration: FileBasedConfiguration

    init {
        val parameters = Parameters()
        configuration = FileBasedConfigurationBuilder<FileBasedConfiguration>(YAMLConfiguration::class.java)
            .configure(parameters.properties().setFileName("application.yml"))
            .configuration
    }

    fun getString(key: String): String {
        return configuration.getString(key)
    }
}
