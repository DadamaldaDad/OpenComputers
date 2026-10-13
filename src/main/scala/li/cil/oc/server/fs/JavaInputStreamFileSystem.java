package li.cil.oc.server.fs;

import li.cil.oc.server.fs.InputStreamFileSystem;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
abstract class JavaInputStreamFileSystem implements InputStreamFileSystem {
    private Map handles = new HashMap();

    protected JavaInputStreamFileSystem() {
        InputStreamFileSystem.$init$(this);
    }

    @Override
    public Map li$cil$oc$server$fs$InputStreamFileSystem$$handles() {
        return handles;
    }

    @Override
    public void li$cil$oc$server$fs$InputStreamFileSystem$_setter_$li$cil$oc$server$fs$InputStreamFileSystem$$handles_$eq(
            Map value) {
        handles = value;
    }
}
