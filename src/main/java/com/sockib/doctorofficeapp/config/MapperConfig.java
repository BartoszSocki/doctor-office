package com.sockib.doctorofficeapp.config;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
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

}
