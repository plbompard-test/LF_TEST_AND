package plbompard.exploration.network.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import plbompard.exploration.models.Restaurant;

/**
 * Created by plbompard on 02/09/2016.
 */
public class ExplorationObjectMapper {

    public static ObjectMapper instance() {
        return LazyHolder.INSTANCE;
    }

    public static ObjectReader restaurantReader() {
        return LazyHolder.RESTAURANT_READER;
    }

    private static class LazyHolder {
        private static final ObjectMapper INSTANCE = buildObjectMapper();

        private static final ObjectReader RESTAURANT_READER = buildRestaurantReader();

        private static ObjectMapper buildObjectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper;
        }

        private static ObjectReader buildRestaurantReader() {
            return INSTANCE.readerFor(Restaurant.class);
        }
    }



}
