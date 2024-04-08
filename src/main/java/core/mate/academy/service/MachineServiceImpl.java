package core.mate.academy.service;

import core.mate.academy.model.Bulldozer;
import core.mate.academy.model.Excavator;
import core.mate.academy.model.Machine;
import core.mate.academy.model.Truck;
import core.mate.academy.service.producers.BulldozerProducer;
import core.mate.academy.service.producers.ExcavatorProducer;
import core.mate.academy.service.producers.MachineProducer;
import core.mate.academy.service.producers.TruckProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementation of MachineService.
 */
public class MachineServiceImpl implements MachineService<Machine> {
    private static final Map<String, ? extends MachineProducer<Machine>> MACHINE_PRODUCER =
            new HashMap<>() {{
                    put(Bulldozer.class.getSimpleName(), new BulldozerProducer());
                    put(Truck.class.getSimpleName(), new TruckProducer());
                    put(Excavator.class.getSimpleName(), new ExcavatorProducer());
                }};

    @Override
    public List<Machine> getAll(Class<? extends Machine> type) {
        String classType = type.getSimpleName();
        if (MACHINE_PRODUCER.containsKey(classType)) {
            return (List<Machine>) MACHINE_PRODUCER.get(classType).get();
        }
        return new ArrayList<>();
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



