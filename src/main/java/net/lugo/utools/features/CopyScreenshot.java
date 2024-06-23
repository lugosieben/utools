package net.lugo.utools.features;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class CopyScreenshot {

    // TY: https://github.com/Declipsonator/Copyshot/blob/main/src/main/java/me/declipsonator/copyshot/util/ClipboardImage.java
    public record TransferableImage(Image image) implements Transferable {

        public @NotNull Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (isDataFlavorSupported(flavor)) return image;
            throw new UnsupportedFlavorException(flavor);
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor == DataFlavor.imageFlavor;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }
    }
}
