package io.holunda.camunda.bpm.data.spin

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.cibseven.spin.impl.json.jackson.format.JacksonJsonDataFormat
import org.cibseven.spin.spi.DataFormatConfigurator

class KotlinJacksonDataFormatConfigurator : DataFormatConfigurator<JacksonJsonDataFormat> {

  override fun configure(dataFormat: JacksonJsonDataFormat) {
    val objectMapper = dataFormat.objectMapper
    objectMapper.registerModule(KotlinModule.Builder().build())
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
  }

  override fun getDataFormatClass(): Class<JacksonJsonDataFormat> = JacksonJsonDataFormat::class.java
}
