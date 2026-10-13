package li.cil.oc.common.component;

import li.cil.oc.api.internal.TextBuffer;
import li.cil.oc.common.component.traits.VideoRamRasterizer;
import net.minecraft.core.component.DataComponentHolder;
import scala.Option;

public final class ClientGpuTextBufferHandler {
    private ClientGpuTextBufferHandler() { }

    public static void bitblt(TextBuffer dst, int col, int row, int width, int height,
                              String owner, int srcId, int fromCol, int fromRow) {
        if (!(dst instanceof VideoRamRasterizer device)) return;
        Option<GpuTextBuffer> source = device.getBuffer(owner, srcId);
        if (source.isDefined()) GpuTextBuffer.bitblt(dst, col, row, width, height, source.get(), fromCol, fromRow);
    }

    public static boolean removeBuffer(TextBuffer buffer, String owner, int id) {
        return buffer instanceof VideoRamRasterizer device && device.removeBuffer(owner, id);
    }

    public static boolean loadBuffer(TextBuffer buffer, String owner, int id, DataComponentHolder data) {
        return buffer instanceof VideoRamRasterizer device && device.loadBuffer(owner, id, data);
    }
}