package se.fk.github.rimfrost.handlaggning.logic.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;
import se.fk.github.rimfrost.handlaggning.logic.exception.HandlaggningNotFoundException;
import se.fk.github.rimfrost.handlaggning.logic.repository.HandlaggningRepository;
import se.fk.github.rimfrost.handlaggning.logic.service.HandlaggningService;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicMapper;

@ApplicationScoped
public class HandlaggningServiceImpl implements HandlaggningService
{
   @Inject
   HandlaggningRepository handlaggningRepository;

   @Inject
   LogicMapper mapper;

   @Override
   public HandlaggningGetResponse getHandlaggning(HandlaggningGetRequest request)
   {
      var handlaggningEntity = handlaggningRepository.findById(request.handlaggningId())
            .orElseThrow(() -> new HandlaggningNotFoundException("Handlaggning not found for id: " + request.handlaggningId()));
      return ImmutableHandlaggningGetResponse.builder()
            .handlaggning(mapper.toHandlaggningDTO(handlaggningEntity))
            .build();
   }

   @Override
   public HandlaggningPutResponse putHandlaggning(HandlaggningPutRequest request)
   {
      var entity = mapper.toHandlaggningEntity(request.handlaggning());

      handlaggningRepository.save(entity);

      var yrkande = ImmutableYrkandeDTO.builder()
            .from(mapper.toYrkandeDTO(entity.yrkande()))
            .beslut(request.handlaggning().yrkande().beslut())
            .build();

      var handlaggning = ImmutableHandlaggningDTO.builder()
            .from(mapper.toHandlaggningDTO(entity))
            .yrkande(yrkande)
            .uppgift(request.handlaggning().uppgift())
            .underlag(request.handlaggning().underlag())
            .build();

      return ImmutableHandlaggningPutResponse.builder()
            .handlaggning(handlaggning)
            .build();
   }
}
