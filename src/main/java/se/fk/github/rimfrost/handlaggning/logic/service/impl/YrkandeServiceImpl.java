package se.fk.github.rimfrost.handlaggning.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.dto.YrkandeCreateRequest;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.ImmutableYrkandeEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.*;
import se.fk.github.rimfrost.handlaggning.logic.enums.YrkandestatusEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.ProduktvariantRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.IndividRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.RollRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.YrkandeRepository;
import se.fk.github.rimfrost.handlaggning.logic.service.YrkandeService;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicEnumMapper;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicMapper;

@ApplicationScoped
public class YrkandeServiceImpl implements YrkandeService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(YrkandeService.class);

   @Inject
   private YrkandeRepository yrkandeRepository;

   @Inject
   RollRepository rollRepository;

   @Inject
   ProduktvariantRepository produktvariantRepository;

   @Inject
   IndividRepository individRepository;

   @Inject
   private LogicMapper mapper;

   @Inject
   LogicEnumMapper enumMapper;

   @Override
   public YrkandeCreateResponse createYrkande(YrkandeCreateRequest request)
   {
      PeriodEntity periodEntity = ImmutablePeriodEntity.builder()
            .start(request.start())
            .slut(request.slut())
            .build();

      List<YrkanderollEntity> yrkanderollEntities = new ArrayList<>();
      for (var person : request.person())
      {
         // TODO: Replace IndividRepository call with suitable logic when switching to new model
         IndividEntity individEntity = individRepository.findByPersonnummer(person.persnr()).orElseThrow();
         var roll = rollRepository.findById(person.roll()).orElseThrow();

         YrkanderollEntity yrkanderollEntity = ImmutableYrkanderollEntity.builder()
               .id(UUID.randomUUID())
               .individ(individEntity)
               .roll(roll)
               .yrkande(person.yrkande())
               .build();

         yrkanderollEntities.add(yrkanderollEntity);
      }

      List<ErsattningEntity> ersattningar = new ArrayList<>();
      for (var ersattning : request.ersattning())
      {
         var produktvariant = produktvariantRepository.findById(ersattning.produktvariantId()).orElseThrow();
         var beloppstyp = produktvariantRepository.findBeloppstypById(ersattning.produktvariantId()).orElseThrow();
         var berakningsgrund = produktvariantRepository.findBerakningsgrundById(ersattning.produktvariantId()).orElseThrow();

         ProduceratResultatEntity pre = ImmutableProduceratResultatEntity.builder()
               .id(UUID.randomUUID())
               .version("1.0")
               .franOchMed(ersattning.period().start())
               .tillOchMed(ersattning.period().slut())
               .status(ErsattningsstatusEntity.PLANERAT)
               .build();

         ErsattningEntity ersattningEntity = ImmutableErsattningEntity.builder()
               .id(UUID.randomUUID())
               .belopp(0)
               .berakningsgrund(berakningsgrund)
               .beloppstyp(beloppstyp)
               .produktvariant(produktvariant)
               .periodisering(enumMapper.toPeriodiseringEntity(ersattning.periodisering()))
               .omfattning(ersattning.omfattning())
               .beslutsutfall(BeslutsutfallEntity.FU)
               .avslagsanledning("")
               .produceratResultat(pre)
               .build();

         ersattningar.add(ersattningEntity);
      }

      YrkandeEntity yrkandeEntity = ImmutableYrkandeEntity.builder()
            .id(UUID.randomUUID())
            .formanstyp(request.formanstyp().toString())
            .version("1.0")
            .yrkandedatum(OffsetDateTime.now())
            .yrkandestatus(YrkandestatusEntity.FASTSTALLT)
            .period(periodEntity)
            .avsikt(AvsiktEntity.NY)
            .andringsorsak("")
            .yrkanderoll(yrkanderollEntities)
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
