package com.sockib.doctorofficeapp.config;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Bean
    public TypeMap<ScheduledVisit, ScheduledVisitDto> scheduledVisitToScheduledVisitDtoTypeMap(ModelMapper modelMapper) {
        TypeMap<ScheduledVisit, ScheduledVisitDto> scheduledVisitMapper =
                modelMapper.createTypeMap(ScheduledVisit.class, ScheduledVisitDto.class);
        scheduledVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredDoctor().getId(), ScheduledVisitDto::setRegisteredDoctorId));

        return scheduledVisitMapper;
    }

    @Bean
    public TypeMap<PlannedVisit, PlannedVisitDto> plannedVisitPlannedVisitDtoTypeMap(ModelMapper modelMapper) {
        TypeMap<PlannedVisit, PlannedVisitDto> plannedVisitMapper =
                modelMapper.createTypeMap(PlannedVisit.class, PlannedVisitDto.class);
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getScheduledVisit().getVisitBegTime(), PlannedVisitDto::setBegTime));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getScheduledVisit().getVisitEndTime(), PlannedVisitDto::setEndTime));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getScheduledVisit().getAddress().getCity(), PlannedVisitDto::setCity));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getScheduledVisit().getAddress().getStreet(), PlannedVisitDto::setStreet));

        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getVisitStatus().getCanceled(), PlannedVisitDto::setCancelled));

        // Doctor
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredDoctor().getId(), PlannedVisitDto::setRegisteredDoctorId));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredDoctor().getName(), PlannedVisitDto::setDoctorName));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredDoctor().getSurname(), PlannedVisitDto::setDoctorSurname));

        // Client
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredClient().getId(), PlannedVisitDto::setRegisteredClientId));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredClient().getName(), PlannedVisitDto::setClientName));
        plannedVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredClient().getSurname(), PlannedVisitDto::setClientSurname));

        return plannedVisitMapper;
    }

}
