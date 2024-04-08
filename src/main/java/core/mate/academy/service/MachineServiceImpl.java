package core.mate.academy.service;

import core.mate.academy.model.Bulldozer;
import core.mate.academy.model.Excavator;
import core.mate.academy.model.Machine;
import core.mate.academy.model.Truck;
import core.mate.academy.service.producers.BulldozerProducer;
import core.mate.academy.service.producers.ExcavatorProducer;
import core.mate.academy.service.producers.MachineProducer;
import core.mate.academy.service.producers.TruckProducer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Your implementation of MachineService.
 */
public class MachineServiceImpl implements MachineService<Machine> {
    private static final Map<String,
            ? extends MachineProducer<? extends Machine>> MACHINE_PRODUCERS =
            Map.of(Bulldozer.class.getSimpleName(), new BulldozerProducer(),
                Truck.class.getSimpleName(), new TruckProducer(),
                Excavator.class.getSimpleName(), new ExcavatorProducer());

    @Override
    public List<Machine> getAll(Class<? extends Machine> type) {
        String classType = type.getSimpleName();
        if (MACHINE_PRODUCERS.containsKey(classType)) {
            return (List<Machine>) MACHINE_PRODUCERS.get(classType).get();
        }
        return Collections.emptyList();
    }

    @Override
    public void fill(List<Object> machines, Machine value) {
        machines.replaceAll(ignored -> value);
    }

    @Override
    public void startWorking(List<? extends Machine> machines) {
        for (Machine machine : machines) {
            machine.doWork();
        }
    }
}
