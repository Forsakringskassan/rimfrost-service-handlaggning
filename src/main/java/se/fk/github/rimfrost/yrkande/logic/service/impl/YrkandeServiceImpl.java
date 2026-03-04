package se.fk.github.rimfrost.yrkande.logic.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeCreateRequest;
import se.fk.github.rimfrost.yrkande.logic.entity.*;
import se.fk.github.rimfrost.yrkande.logic.entity.ImmutableYrkandeEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.*;
import se.fk.github.rimfrost.yrkande.logic.enums.YrkandestatusEntity;
import se.fk.github.rimfrost.yrkande.logic.repository.YrkandeRepository;
import se.fk.github.rimfrost.yrkande.logic.service.YrkandeService;
import se.fk.github.rimfrost.yrkande.logic.util.LogicMapper;

@ApplicationScoped
public class YrkandeServiceImpl implements YrkandeService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(YrkandeService.class);

   @Inject
   private YrkandeRepository yrkandeRepository;

   @Inject
   private LogicMapper mapper;

   @Override
   public YrkandeCreateResponse createYrkande(YrkandeCreateRequest request)
   {
      OffsetDateTime start = request.start();
      OffsetDateTime slut = request.slut();
      LocalDate startDate = start.toLocalDate();
      LocalDate endDate = slut.toLocalDate();

      long days = ChronoUnit.DAYS.between(start.toLocalDate(), slut.toLocalDate()) + 1;

      PeriodEntity periodEntity = ImmutablePeriodEntity.builder()
            .start(request.start())
            .slut(request.slut())
            .build();

      // TODO: Ta bort hårdkodning av namn
      IndividEntity individEntity = ImmutableIndividEntity.builder()
            .id(request.persnr())
            .fornamn("Lisa")
            .efternamn("Tass")
            .build();

      YrkanderollEntity yrkanderollEntity = ImmutableYrkanderollEntity.builder()
            .id(UUID.randomUUID())
            .individ(individEntity)
            .roll(RollEntity.AGARE)
            .yrkande(true)
            .build();

      List<ErsattningEntity> ersattningar = new ArrayList<>();
      for (int i = 0; i < days; i++)
      {
         LocalDate currentDate = startDate.plusDays(i);

         OffsetDateTime franOchMed;
         OffsetDateTime tillOchMed;

         if (i == 0)
         {
            // First day
            franOchMed = start;

            if (startDate.equals(endDate))
            {
               tillOchMed = slut;
            }
            else
            {
               tillOchMed = currentDate.atTime(LocalTime.MAX).atOffset(start.getOffset());
            }
         }
         else if (currentDate.equals(endDate))
         {
            // Last day
            franOchMed = currentDate.atStartOfDay().atOffset(slut.getOffset());
            tillOchMed = slut;
         }
         else
         {
            // Middle day
            franOchMed = currentDate.atStartOfDay().atOffset(start.getOffset());
            tillOchMed = currentDate.atTime(LocalTime.MAX).atOffset(start.getOffset());
         }

         ProduceratResultatEntity pre = ImmutableProduceratResultatEntity.builder()
               .id(UUID.randomUUID())
               .version("1.0")
               .franOchMed(franOchMed)
               .tillOchMed(tillOchMed)
               .status(ErsattningsstatusEntity.PLANERAT)
               .build();

         ErsattningEntity ersattningEntity = ImmutableErsattningEntity.builder()
               .id(UUID.randomUUID())
               .belopp(40000)
               .berakningsgrund(BerakningsgrundEntity.LON)
               .beloppstyp(BeloppstypEntity.INKOMSTBASERAD)
               .ersattningstyp(ErsattningstypEntity.HUNDBIDRAG)
               .periodisering(PeriodiseringEntity.DAG)
               .omfattning(100)
               .beslutsutfall(BeslutsutfallEntity.FU)
               .avslagsanledning("")
               .produceratResultat(pre)
               .build();

         ersattningar.add(ersattningEntity);
      }

      YrkandeEntity yrkandeEntity = ImmutableYrkandeEntity.builder()
            .id(UUID.randomUUID())
            .formanstyp(request.formanstyp())
            .version("1.0")
            .yrkandedatum(OffsetDateTime.now())
            .yrkandestatus(YrkandestatusEntity.FASTSTALLT)
            .period(periodEntity)
            .avsikt(AvsiktEntity.NY)
            .andringsorsak("")
            .yrkanderoll(List.of(yrkanderollEntity))
            .ersattning(ersattningar)
            .build();

      yrkandeRepository.save(yrkandeEntity);

      YrkandeCreateResponse yrkandeCreateResponse = ImmutableYrkandeCreateResponse.builder()
            .yrkande(mapper.toYrkandeDTO(yrkandeEntity))
            .build();

      return yrkandeCreateResponse;
   }

   @Override
   public YrkandeGetResponse getById(YrkandeGetRequest yrkandeGetRequest)
   {
      UUID id = yrkandeGetRequest.yrkandeId();
      YrkandeEntity yrkandeEntity = yrkandeRepository.findById(id).orElse(null);
      YrkandeGetResponse yrkandeGetResponse = ImmutableYrkandeGetResponse.builder()
            .yrkande(mapper.toYrkandeDTO(yrkandeEntity))
            .build();
      return yrkandeGetResponse;
   }

}
