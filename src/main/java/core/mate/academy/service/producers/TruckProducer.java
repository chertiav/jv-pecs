package core.mate.academy.service.producers;

import core.mate.academy.model.Machine;
import core.mate.academy.model.Truck;
import java.util.List;

public class TruckProducer implements MachineProducer<Machine> {
    @Override
    public List<Truck> get() {
        return List.of(new Truck());
    }
}
