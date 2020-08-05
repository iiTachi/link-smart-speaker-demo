package link.smart.speaker.demo.ai.dueros.entity.response.query;

import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import lombok.Data;

import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/6/22
 */
@Data
public class DuerosTemperatureReadingPayload extends DuerosResponsePayload {
    Map<String, Object> temperatureReading;
}
