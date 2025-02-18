package org.cibseven.community.bpm.data.spin;

import org.cibseven.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.cibseven.spin.spi.DataFormatConfigurator;

/**
 * Spin configurator referenced in META-INF/services/org.cibseven.spin.spi.DataFormatConfigurator.
 * Configures the {@link ErasedCollectionTypeDetector}.
 */
public class SpinTypeDetectorConfigurator implements DataFormatConfigurator<JacksonJsonDataFormat> {

  @Override
  public Class<JacksonJsonDataFormat> getDataFormatClass() {
    return JacksonJsonDataFormat.class;
  }

  @Override
  public void configure(JacksonJsonDataFormat dataFormat) {
    dataFormat.addTypeDetector(ErasedCollectionTypeDetector.INSTANCE);
  }
}
