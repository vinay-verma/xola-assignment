package com.xola.assignment.service;

import com.xola.assignment.dto.InputDto;
import com.xola.assignment.exception.AssignmentException;
import com.xola.assignment.model.Coordinate;
import com.xola.assignment.model.Person;
import com.xola.assignment.model.Region;
import com.xola.assignment.model.Town;
import com.xola.assignment.model.enums.Movement;
import com.xola.assignment.model.enums.PersonType;
import com.xola.assignment.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TraceService {
    private static final Logger logger = LoggerFactory.getLogger(TraceService.class);

    public String traceMovement(InputDto input) {
        // TODO: 25/07/21 validate input dto
        InputDto.Grid grid = input.getGrid();
        Set<Coordinate> infectedCells = input.getInfectedCells().stream().map(c -> new Coordinate(c.getX(), c.getY())).collect(Collectors.toSet());
        Set<Person> persons = input.getPersons().stream().map(
                p -> new Person(
                        CommonUtils.getCoordinateFromPositionString(p.getInitialPosition()),
                        p.getMovement(),
                        p.getType()
                )
        ).collect(Collectors.toSet());

        Region region = new Region(grid.getLength(), grid.getBreadth(), infectedCells);
        input.getMedicalCentres().forEach(m -> {
            region.getTown(new Coordinate(m.getX(), m.getY())).setMedicalCenter(true);
        });
        return traceMovement(region, persons).printInfectionState();
    }

    private Region traceMovement(Region region, Set<Person> persons) {
        for (Person person : persons) {
            logger.debug("Tracing movement for person: " + person);
            char[] movements = person.getMovements().toCharArray();
            for (char movement : movements) {
                logger.debug("Movement Action: " + movement);
                switch (Movement.valueOf(String.valueOf(movement))) {
                    case R:
                        person.turnRight();
                        logger.debug("Updated Person Coordinates: " + person.getCurrentPosition() + ", infected: " + person.isInfected());
                        break;
                    case L:
                        person.turnLeft();
                        logger.debug("Updated Person Coordinates: " + person.getCurrentPosition() + ", infected: " + person.isInfected());
                        break;
                    case F:
                        Coordinate newPosition = person.moveForward();
                        logger.debug("Updated Person Coordinates: " + person.getCurrentPosition());
                        Town currentTown = region.getTown(newPosition);
                        if (currentTown == null) {
                            throw new AssignmentException("Town could not be found with coordinate " + newPosition);
                        }
                        if (person.getType().equals(PersonType.medic)) {
                            logger.debug("Medic Visit, Town cured " +  person.getCurrentPosition());
                            currentTown.cure();
                        } else if (person.isInfected()) {
                            if (currentTown.isImmune()) {
                                person.cure();
                            } else if (currentTown.isInfected()) {
                                logger.debug("Town already infected" + currentTown.getCoordinate());
                            } else if (!region.isNeiboursOfAMedicalCenter(currentTown.getCoordinate())) {
                                currentTown.infect();
                                logger.debug("Infecting town: " + currentTown.getCoordinate());
                            }
                        } else if (currentTown.isInfected()) {
                            if (person.isInfected()) {
                                logger.debug("Person already infected");
                            } else {
                                person.infect();
                                logger.debug("Infecting person: " + person.getCurrentPosition());
                            }
                        } else {
                            logger.debug("No sign of infection so far");
                        }
                }
            }
        }
        return region;
    }
}
