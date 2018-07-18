package org.dozer.testcase;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.Mapping;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test case for https://github.com/DozerMapper/dozer/issues/667
 * <p>
 * Dozer is duplicating destination collection's items if @Mapping is used together with class level accessible=true.
 *
 * @author leonardo.cruz
 */
public class MappingDuplicatesCollectionItemsTest {

    private static final int ONE_ELEMENT = 1;

    @Test
    public void testMappingMixingClassLevelAccessibleAndMappingAnnotation() {

        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingFiles("mapping.xml")
                .build();

        Source source = new Source(Arrays.asList("item-a"));
        Destination destination = mapper.map(source, Destination.class);

        Assert.assertEquals(destination.getItemsSize(), ONE_ELEMENT);

    }

    public static class Source {

        private List<String> items;

        public Source(List<String> items) {
            this.items = items;
        }
    }

    public static class Destination {

        @Mapping
        private List<String> items;

        public int getItemsSize() {
            return items.size();
        }
    }

}
