package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.UUID;

import org.immutables.value.Value;

@Value.Immutable
public interface ErbjudandeFlowInfoEntity
{

   UUID id();

   String erbjudandetyp();

   String bpmn();

   String namn();

   String beskrivning();

   String kafkaTopic();

   String kafkaRequestType();
}
