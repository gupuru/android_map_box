package kohei.mapboxtest;

import com.squareup.otto.Bus;

/**
 * Otto singleton
 */
public final class BusHolder {
    private static final Bus mBus = new Bus();

    public static Bus get() {
        return mBus;
    }
}