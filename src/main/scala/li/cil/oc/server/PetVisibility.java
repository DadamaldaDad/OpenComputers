package li.cil.oc.server;

import scala.collection.mutable.Set;
import scala.jdk.javaapi.CollectionConverters;

import java.util.HashSet;

public final class PetVisibility {
    public static final Set<String> hidden = CollectionConverters.asScala(new HashSet<>());

    private PetVisibility() {
    }
}
