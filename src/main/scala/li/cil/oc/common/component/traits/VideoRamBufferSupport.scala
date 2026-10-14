package li.cil.oc.common.component.traits

import li.cil.oc.util.{PackedColor, TextBuffer}

private[traits] object VideoRamBufferSupport {
  def newBuffer(): TextBuffer = new TextBuffer(1, 1, PackedColor.SingleBitFormat)
}
